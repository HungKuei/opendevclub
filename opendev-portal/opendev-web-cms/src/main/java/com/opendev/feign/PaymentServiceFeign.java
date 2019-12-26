package com.opendev.feign;

import com.opendev.api.service.PaymentService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("pay-server")
public interface PaymentServiceFeign extends PaymentService {
}
