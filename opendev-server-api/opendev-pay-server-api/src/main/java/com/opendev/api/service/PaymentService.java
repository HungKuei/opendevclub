package com.opendev.api.service;

import com.opendev.base.BaseResponse;
import com.opendev.bean.PaymentInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "支付服务接口")
@RequestMapping("/pay")
public interface PaymentService {

    /**
     * 创建支付令牌
     * @param paymentInfo
     * @return
     */
    @ApiOperation(value = "创建支付令牌")
    @PostMapping("/createPayToken")
    BaseResponse createPayToken(@RequestBody PaymentInfo paymentInfo);

    /**
     * 使用支付令牌查询支付信息
     * @param payToken
     * @return
     */
    @ApiOperation(value = "使用支付令牌查询支付信息")
    @GetMapping("/getPayToken")
    BaseResponse getPayToken(@RequestParam("payToken") String payToken);

    /**
     * 同步通知 获取支付宝GET过来反馈信息
     * @param params
     * @return
     */
    @ApiOperation(value = "同步通知 获取支付宝GET过来反馈信息")
    @GetMapping("/back/synNotify")
    BaseResponse synNotify(@RequestParam Map<String, String> params);

    /**
     * 异步通知 获取支付宝POST过来反馈信息
     * @param params
     * @return
     */
    @ApiOperation(value = "异步通知 获取支付宝POST过来反馈信息")
    @PostMapping("/back/asynNotify")
    String asynNotify(@RequestParam Map<String, String> params);

}
