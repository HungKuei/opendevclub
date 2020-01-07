package com.opendev.feign;

import com.opendev.api.hystrix.BbsServiceHystrix;
import com.opendev.api.service.BbsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(value = "bbs-server", fallback = BbsServiceHystrix.class)
public interface BbsServiceFeign extends BbsService {

}
