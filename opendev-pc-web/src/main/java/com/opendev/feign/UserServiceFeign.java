package com.opendev.feign;

import com.opendev.api.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@Component
@FeignClient("user-server")
public interface UserServiceFeign extends UserService {
}
