package com.opendev.controller;

import com.opendev.base.APIResponse;
import com.opendev.enums.ResultStatusCode;
import org.springframework.web.bind.annotation.*;

/**
 * 公共接口层
 */
@RestController
@RequestMapping("/common")
public class CommonController {


    /**
     * 未登录认证
     * @return
     */
    @GetMapping("/unauth")
    public APIResponse unauth(){
        return new APIResponse(ResultStatusCode.UNAUTHO_ERROR);
    }


    /**
     * 被踢出后跳转方法
     * @return
     */
    @GetMapping("/kickout")
    public APIResponse kickout(){
        return new APIResponse(ResultStatusCode.INVALID_TOKEN);
    }

    /**
     * 错误请求处理
     * @param code
     * @return
     */
    @GetMapping("/error/{code}")
    public APIResponse error(@PathVariable("code") Integer code){
        if (code == 404){
            return new APIResponse(ResultStatusCode.NOT_FOUND);
        }else{
            return new APIResponse(ResultStatusCode.SYSTEM_ERR);
        }
    }
}