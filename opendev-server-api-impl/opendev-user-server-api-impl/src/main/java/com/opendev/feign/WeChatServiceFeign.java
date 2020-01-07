package com.opendev.feign;

import com.opendev.api.hystrix.WeChatServiceHystrix;
import com.opendev.api.service.WeChatService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "wechat_server", fallback = WeChatServiceHystrix.class)
public interface WeChatServiceFeign extends WeChatService {
}
