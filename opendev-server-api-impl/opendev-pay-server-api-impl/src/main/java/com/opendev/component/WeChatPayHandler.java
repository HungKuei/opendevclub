package com.opendev.component;

import com.opendev.handler.PayHandler;
import org.springframework.stereotype.Component;

/**
 * 聚合微信支付
 */
@Component
public class WeChatPayHandler implements PayHandler {

    @Override
    public String getPayHtml() {
        /**
         * 微信支付业务实现
         */
        return "调用微信支付接口";
    }
}
