package com.opendev.component;

import com.opendev.handler.PayHandler;
import org.springframework.stereotype.Component;

/**
 * 聚合银联支付
 */
@Component
public class UnionPayHandler implements PayHandler {

    @Override
    public String getPayHtml() {
        /**
         * 银联支付业务实现
         */
        return "调用银联支付接口";
    }
}
