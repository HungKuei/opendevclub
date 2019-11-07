package com.opendev.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.config.AlipayConfig;
import com.opendev.api.service.RetracementService;
import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class RetracementServiceImpl extends BaseService implements RetracementService {

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
    public String asynNotify(@RequestParam Map<String, String> params) {
        return "success";
    }
}
