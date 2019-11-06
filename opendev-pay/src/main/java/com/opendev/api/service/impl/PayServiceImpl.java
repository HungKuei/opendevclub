package com.opendev.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import com.opendev.api.service.PayService;
import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import com.opendev.constant.PublicConstant;
import com.opendev.mapper.PaymentMapper;
import com.opendev.model.PaymentInfo;
import com.opendev.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class PayServiceImpl extends BaseService implements PayService {

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
    public BaseResponse testNork(@RequestParam("username") String username) {
        return success(username);
    }
}
