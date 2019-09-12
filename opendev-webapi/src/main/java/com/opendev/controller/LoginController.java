package com.opendev.controller;

import com.opendev.base.APIResponse;
import com.opendev.enums.ResultStatusCode;
import com.opendev.model.User;
import com.opendev.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Deque;


/**
 * 登录接口层
 */

@Api(value = "LoginController", description = "登录接口")
@RequestMapping("/api")
@RestController
public class LoginController {

    @Autowired
    private RedisCacheManager redisCacheManager;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "rememberMe", value = "记住我", required = true, dataType = "Integer", paramType = "form")
    })
    @PostMapping("/login")
    public APIResponse login(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("rememberMe") Integer rememberMe) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            token.setRememberMe(rememberMe != 1 ? false : true);
            subject.login(token);
            if (subject.isAuthenticated()) {
                User user = (User) subject.getPrincipal();
                return JsonResult.success(ResultStatusCode.SUCCESS.getCode(),"登录成功", user);
            } else {
                return new APIResponse(ResultStatusCode.SHIRO_ERROR);
            }
        }catch (UnknownAccountException e) {
            return new APIResponse(ResultStatusCode.NOT_EXIST_USER);
        } catch (IncorrectCredentialsException e) {
            return new APIResponse(ResultStatusCode.ACCOUNT_OR_PWD_ERROR);
        } catch (LockedAccountException e) {
            return new APIResponse(ResultStatusCode.USER_FROZEN.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "登出")
    @PostMapping("/logout")
    public APIResponse logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            String username = ((User) SecurityUtils.getSubject().getPrincipal()).getUsername();
            Serializable sessionId = SecurityUtils.getSubject().getSession().getId();
            Cache<Serializable, Deque<Serializable>> cache = redisCacheManager.getCache("USER");
            Deque<Serializable> deques = cache.get(username);
            if (!deques.isEmpty()){
                for (Serializable deque : deques){
                    if (sessionId.equals(deque)){
                        deques.remove(deque);
                        break;
                    }
                }
            }
            cache.put(username, deques);
        }
        subject.logout();
        return new APIResponse(ResultStatusCode.SUCCESS);
    }
}
