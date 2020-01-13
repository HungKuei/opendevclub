package com.opendev.hystrix;

import com.opendev.api.service.WeChatService;
import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信接口服务熔断处理
 */
@Component
public class WeChatServiceHystrix extends BaseService implements WeChatService {

    @Override
    public String dispatcherGet(@RequestParam("signature") String signature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestParam("echostr") String echostr) {
        return null;
    }

    @Override
    public BaseResponse validateWeChatCode(@RequestParam("phone") String phone, @RequestParam("registCode") String registCode) {
        return error("访问超时");
    }

    @Override
    public void dispatcherGet(@RequestParam("request") HttpServletRequest request, @RequestParam("response") HttpServletResponse response) throws Exception {

    }
}
