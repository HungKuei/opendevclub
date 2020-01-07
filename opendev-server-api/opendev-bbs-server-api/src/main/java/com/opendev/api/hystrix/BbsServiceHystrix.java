package com.opendev.api.hystrix;

import com.opendev.api.service.BbsService;
import com.opendev.base.BaseResponse;
import com.opendev.bean.BbsBlog;
import org.springframework.stereotype.Component;

/**
 * 论坛内容维护 熔断处理
 * @author hungkuei.
 * @create 2020-01-03 上午11:40
 */
@Component
public class BbsServiceHystrix implements BbsService{

    @Override
    public BaseResponse getLatestBbs(Long userId, int offset, int limit) {
        return null;
    }

    @Override
    public BaseResponse getByBbsId(Long bbsId) {
        return null;
    }

    @Override
    public BaseResponse addBbs(BbsBlog bbsBlog) {
        return null;
    }
}
