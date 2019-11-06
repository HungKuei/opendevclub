package com.opendev.api.service;

import com.opendev.base.BaseResponse;
import com.opendev.model.PaymentInfo;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pay")
public interface PayService {

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

    @RequestMapping("/norok")
    BaseResponse testNork(@RequestParam("username") String username);
}
