package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.enums.ResultStatusCode;
import com.opendev.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 登录接口层
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form")
    })
    @PostMapping("login")
    public BaseResponse login(String username, String password) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            if (subject.isAuthenticated()) {
                return JsonResult.success().put("token", subject.getSession().getId());
            } else {
                return new BaseResponse(ResultStatusCode.SHIRO_ERROR);
            }
        } catch (IncorrectCredentialsException | UnknownAccountException e) {
            return new BaseResponse(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
        } catch (LockedAccountException e) {
            return new BaseResponse(ResultStatusCode.USER_FROZEN);
        } catch (Exception e) {
            return new BaseResponse(ResultStatusCode.SYSTEM_ERR);
        }
    }
}
