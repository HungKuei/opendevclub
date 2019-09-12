package com.opendev.controller;

import com.opendev.base.APIResponse;
import com.opendev.enums.ResultStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 公共接口层
 */

@Api(value = "CommonController", description = "公共接口")
@RequestMapping("/common")
@RestController
public class CommonController {


    /**
     * 未登录认证
     * @return
     */
    @ApiOperation(value = "未登录认证接口")
    @GetMapping("/unauth")
    public APIResponse unauth(){
        return new APIResponse(ResultStatusCode.UNAUTHO_ERROR);
    }


    /**
     * 被踢出后跳转方法
     * @return
     */
    @ApiOperation(value = "被踢出后跳转接口")
    @GetMapping("/kickout")
    public APIResponse kickout(){
        return new APIResponse(ResultStatusCode.INVALID_TOKEN);
    }

    /**
     * 错误请求处理
     * @param code
     * @return
     */
    @ApiOperation(value = "错误请求处理接口")
    @GetMapping("/error/{code}")
    public APIResponse error(@PathVariable("code") Integer code){
        if (code == 404){
            return new APIResponse(ResultStatusCode.NOT_FOUND);
        }else{
            return new APIResponse(ResultStatusCode.SYSTEM_ERR);
        }
    }
}