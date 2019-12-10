package com.opendev.component;

import com.opendev.handler.PayHandler;
import org.springframework.stereotype.Component;

/**
 * 聚合支付宝
 */
@Component
public class AliPayHandler implements PayHandler {

    @Override
    public String getPayHtml() {
        /**
         * 支付宝支付业务实现
         */
        return "调用支付宝接口";
    }
}
