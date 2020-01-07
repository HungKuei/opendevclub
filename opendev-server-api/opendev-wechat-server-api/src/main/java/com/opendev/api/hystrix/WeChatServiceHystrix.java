package com.opendev.api.hystrix;

import com.opendev.api.service.WeChatService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信接口服务熔断处理
 */
@Component
public class WeChatServiceHystrix implements WeChatService {

    @Override
    public String dispatcherGet(String signature, String timestamp, String nonce, String echostr) {
        return null;
    }

    @Override
    public void dispatcherGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
