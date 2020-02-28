package com.xxl.sso.server.feign;


import com.opendev.api.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user-server")
public interface UserServiceFeign extends UserService {
}
