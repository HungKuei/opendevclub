package com.opendev.api.service;

import com.opendev.base.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 支付宝回调业务接口
 */
@RequestMapping("/back")
public interface RetracementService {

    /**
     * 同步通知
     * @param params
     * @return
     */
    @RequestMapping("/synNotify")
    BaseResponse synNotify(@RequestParam Map<String, String> params);

    /**
     * 异步通知
     * @param params
     * @return
     */
    @RequestMapping("/asynNotify")
    String asynNotify(@RequestParam Map<String, String> params);
}
