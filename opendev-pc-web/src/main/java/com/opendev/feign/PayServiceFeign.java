package com.opendev.feign;

import com.opendev.api.service.PayService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("pay-server")
public interface PayServiceFeign extends PayService {
}
