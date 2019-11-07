package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.constant.PublicConstant;
import com.opendev.enums.ResultStatusCode;
import com.opendev.feign.UserServiceFeign;
import com.opendev.model.User;
import com.opendev.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

@Controller
public class LoginController extends BaseController {

    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    public static final String REDIRECT_INDEX = "redirect:/";

    @Autowired
    private UserServiceFeign userServiceFeign;

    @GetMapping("register")
    public String register(){
        return REGISTER;
    }

    @PostMapping("register")
    @ResponseBody
    public BaseResponse register(@RequestBody User user){
        return userServiceFeign.register(user);
    }

    @GetMapping("login")
    public String login(HttpServletRequest request){
        if (isAuthentication(request)){
            return REDIRECT_INDEX;
        }
        return LOGIN;
    }

    // 登录请求具体提交实现
    @PostMapping("login")
    @ResponseBody
    public BaseResponse login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletResponse response){
        // 1.验证参数
        // 2.调用登录接口，获取token信息
        BaseResponse res = userServiceFeign.login(username, password);
        if (!res.getCode().equals(PublicConstant.SUCCESS_CODE)){
            return res;
        }
        LinkedHashMap loginData = (LinkedHashMap) res.getData();
        String access_token = (String) loginData.get("access_token");
        if (StringUtils.isEmpty(access_token)){
            return new BaseResponse(ResultStatusCode.SIGN_ERROR);
        }
        // 3.将token信息存放在cookie里面
        setCookie(response, access_token);
        return success("登录成功");
    }

    public void setCookie(HttpServletResponse response, String access_token){
        CookieUtil.addCookie(response, PublicConstant.COOKIE_TOKEN, access_token, PublicConstant.COOKIE_TIMEOUT);
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        if (!isAuthentication(request)){
            return REDIRECT_INDEX;
        }
        String token = getToken(request);
        baseRedisService.delKey(token);
        return REDIRECT_INDEX;
    }
}
