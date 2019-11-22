package com.opendev.api.service;

import com.opendev.base.BaseResponse;
import com.opendev.model.PaymentInfo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/pay")
public interface PaymentService {

    /**
     * 创建支付令牌
     * @param paymentInfo
     * @return
     */
    @PostMapping("/createPayToken")
    BaseResponse createPayToken(@RequestBody PaymentInfo paymentInfo);

    /**
     * 使用支付令牌查询支付信息
     * @param payToken
     * @return
     */
    @GetMapping("/getPayToken")
    BaseResponse getPayToken(@RequestParam("payToken") String payToken);

    /**
     * 同步通知 获取支付宝GET过来反馈信息
     * @param params
     * @return
     */
    @GetMapping("/back/synNotify")
    BaseResponse synNotify(@RequestParam Map<String, String> params);

    /**
     * 异步通知 获取支付宝POST过来反馈信息
     * @param params
     * @return
     */
    @PostMapping("/back/asynNotify")
    String asynNotify(@RequestParam Map<String, String> params);

}
