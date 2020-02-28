package com.opendev.feign;

import com.opendev.api.service.WeChatService;
import com.opendev.hystrix.WeChatServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@Component
@FeignClient(value = "wechat-server", fallback = WeChatServiceHystrix.class)
public interface WeChatServiceFeign extends WeChatService {
}
