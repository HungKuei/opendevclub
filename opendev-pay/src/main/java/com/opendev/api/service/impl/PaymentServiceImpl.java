package com.opendev.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.opendev.api.service.PaymentService;
import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import com.opendev.constant.PublicConstant;
import com.opendev.mapper.PaymentMapper;
import com.opendev.model.PaymentInfo;
import com.opendev.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
public class PaymentServiceImpl extends BaseService implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public BaseResponse createPayToken(@RequestBody PaymentInfo paymentInfo) {
        // 创建请求支付信息
        paymentInfo.setId(UUID.randomUUID().toString());
        Integer payment = paymentMapper.insert(paymentInfo);
        if (payment <= 0){
            return error("创建支付订单失败");
        }
        // 生成支付令牌
        String payToken = TokenUtil.getPayToken();
        // 将支付令牌存放redis，key为payToken value为支付id
        baseRedisService.setString(payToken, paymentInfo.getId(), PublicConstant.PAY_TOKEN_TIMEOUT);
        // 返回支付令牌
        JSONObject payTokenJson = new JSONObject();
        payTokenJson.put("payToken", payToken);
        return success(payTokenJson);
    }

    @Override
    public BaseResponse getPayToken(@RequestParam("payToken") String payToken) {
        // 参数校验
        if (StringUtils.isEmpty(payToken)){
            return error("支付令牌为空");
        }
        // 判断支付令牌是否过期,
        String payId = (String) baseRedisService.getString(payToken);
        if (StringUtils.isEmpty(payId)){
            return error("支付请求已过期");
        }
        // 使用支付id查询支付信息
        PaymentInfo paymentInfo = paymentMapper.selectByPayId(payId);

        if (StringUtils.isEmpty(paymentInfo)){
            return error("未查询到支付信息");
        }

        /**
         *  使用支付id进行支付
         *  对接支付代码 返回提交支付from表单元素给客户端
         *  获得初始化的AlipayClient
         */
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);

        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = paymentInfo.getOrderId();
        // 付款金额，必填 企业金额
        String total_amount = paymentInfo.getPrice() + "";
        // 订单名称，必填
        String subject = "OpenDevClub开源社区会员充值";
        // 商品描述，可空
        // String body = new
        // String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\","
                // + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        // 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        // alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no
        // +"\","
        // + "\"total_amount\":\""+ total_amount +"\","
        // + "\"subject\":\""+ subject +"\","
        // + "\"body\":\""+ body +"\","
        // + "\"timeout_express\":\"10m\","
        // + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        // 请求
        try {
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            JSONObject data = new JSONObject();
            data.put("payHtml", result);
            return success(data);
        } catch (Exception e) {
            return error("支付异常");
        }
    }

    @Override
    public BaseResponse synNotify(@RequestParam Map<String, String> params) {
        // 记录执行日志
        log.info(">>>>>>>>支付宝同步通知开始：{}", params);
        try {
            //调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            //——请在这里编写您的程序（以下代码仅作参考）——
            if(!signVerified) {
                error("验签失败");
            }
            //商户订单号
            String outTradeNo = params.get("out_trade_no");
            //支付宝交易号
            String tradeNo = params.get("trade_no");
            //付款金额
            String totalAmount = params.get("total_amount");
            JSONObject resultJson = new JSONObject();
            resultJson.put("outTradeNo", outTradeNo);
            resultJson.put("tradeNo", tradeNo);
            resultJson.put("totalAmount", totalAmount);
            return success(resultJson);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error(">>>>>>>>>>支付宝同步通知出现异常，ERROR:{}",e);
            return error("系统错误");
        }finally {
            log.info(">>>>>>>>>>>支付宝同步通知结束：{}", params);
        }
    }

    @Override
    @LcnTransaction //分布式事务注解
    @Transactional  //本地事务注解
    public String asynNotify(@RequestParam Map<String, String> params) {
        // 日志记录
        log.info(">>>>>>>>>>>>>>>>支付宝异步通知开始，params:{}", params);
        // 验签
        try {
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            if (!signVerified){
                return PublicConstant.PAY_FAIL;
            }
            // 去除商户订单号
            String outTradeNo = params.get("out_trade_no");
            PaymentInfo paymentInfo = paymentMapper.selectByPayId(outTradeNo);
            if (paymentInfo == null){
                return PublicConstant.PAY_FAIL;
            }
            // 支付宝重试机制
            Integer state = paymentInfo.getState();
            if (state == 1){
                return PublicConstant.PAY_SUCCESS;
            }
            // 交易金额
            String totalAmount = params.get("total_amount");
            // 判断实际付款金额与商品金额是否一致
            String price = String.valueOf(paymentInfo.getPrice());
            if (!price.equals(totalAmount)){
                return PublicConstant.PAY_FAIL;
            }
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 修改支付表状态
            paymentInfo.setState(1);
            paymentInfo.setPayMessage(params.toString());
            paymentInfo.setPlatformorderId(tradeNo);
            // 手动 begin begin
            Integer update = paymentMapper.updateByPrimaryKeySelective(paymentInfo);
            if (update != 1) {
                return PublicConstant.PAY_FAIL;
            }
            // 调用订单接口通知 支付状态
            //
            //
            // #######################

            return PublicConstant.PAY_SUCCESS;
        } catch (AlipayApiException e) {
            log.error("-------->支付宝异步通知出现异常,ERROR:", e);
            // 回滚 手动回滚
            return PublicConstant.PAY_FAIL;
        }finally {
            log.info("<<<<<<<<<<<<<<<<<支付宝异步通知结束,params:{}", params);
        }
    }
}
