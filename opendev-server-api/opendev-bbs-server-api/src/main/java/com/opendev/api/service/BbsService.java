package com.opendev.api.service;

import com.opendev.base.BaseResponse;
import com.opendev.bean.BbsBlog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(tags = "论坛服务接口")
@RequestMapping("/bbs")
public interface BbsService {

    /**
     * 获取最新论坛
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    @ApiOperation(value = "获取最新论坛")
    @GetMapping("/getLatestBbs")
    BaseResponse getLatestBbs(@RequestParam("userId") Long userId, @RequestParam("offset") int offset, @RequestParam("limit") int limit);

    /**
     * 根据id查询论坛
     * @param bbsId
     * @return
     */
    @ApiOperation(value = "根据id查询论坛")
    @GetMapping("/getByBbsId")
    BaseResponse getByBbsId(@RequestParam("bbsId") Long bbsId);

    /**
     * 新增论坛
     * @param bbsBlog
     * @return
     */
    @ApiOperation(value = "新增论坛")
    @PostMapping("/addBbs")
    BaseResponse addBbs(@RequestBody BbsBlog bbsBlog);

}
