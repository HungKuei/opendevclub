package com.opendev.feign;

import com.opendev.api.service.BbsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("bbs-server")
public interface BbsServiceFeign extends BbsService {

}
