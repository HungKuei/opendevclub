package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.enums.ResultStatusCode;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共接口层
 */
@RequestMapping("/common")
@RestController
public class CommonController {

    /**
     * 未授权跳转方法
     * @return
     */
    @RequestMapping("/unauth")
    public BaseResponse unauth(){
        SecurityUtils.getSubject().logout();
        return new BaseResponse(ResultStatusCode.UNAUTHO_ERROR);
    }

    /**
     * 被踢出后跳转方法
     * @return
     */
    @RequestMapping("/kickout")
    public BaseResponse kickout(){
        return new BaseResponse(ResultStatusCode.INVALID_TOKEN);
    }

}