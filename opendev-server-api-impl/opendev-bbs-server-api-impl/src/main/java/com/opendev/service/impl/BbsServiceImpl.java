package com.opendev.service.impl;

import com.opendev.api.service.BbsService;
import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import com.opendev.bean.BbsBlog;
import com.opendev.mapper.BbsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BbsServiceImpl extends BaseService implements BbsService {

    @Autowired
    private BbsMapper bbsMapper;

    @Override
    public BaseResponse getLatestBbs(@RequestParam("userId") Long userId,@RequestParam("offset") int offset,@RequestParam("limit") int limit) {
        List<BbsBlog> bbsBlogs = bbsMapper.selectLatestBbs(userId, offset, limit);
        Long count = bbsMapper.selectCounts();
        return success(bbsBlogs, count);
    }

    @Override
    public BaseResponse getByBbsId(Long bbsId) {
        BbsBlog bbsBlog = new BbsBlog();
        bbsBlog.setBbsId(bbsId);
        BbsBlog blog = bbsMapper.selectByPrimaryKey(bbsBlog);
        return success(blog);
    }

    @Override
    public BaseResponse addBbs(BbsBlog bbsBlog) {
        int i = bbsMapper.insertSelective(bbsBlog);
        if (i > 0){
            return success();
        }
        return error();
    }
}
