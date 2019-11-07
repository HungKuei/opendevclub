package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import com.opendev.constant.PublicConstant;
import com.opendev.feign.UserServiceFeign;
import com.opendev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class BaseController extends BaseService {

    @Autowired
    private UserServiceFeign userServiceFeign;

    /**
     * 获取当前登录用户信息
     * @param request
     * @return
     */
    public User getCurrentUserInfo(HttpServletRequest request){
        String token = getToken(request);
        if (token == null){
            return null;
        }
        // 用token换取userId
        String userId = (String) baseRedisService.getString(token);
        if (StringUtils.isEmpty(userId)){
            return null;
        }
        BaseResponse<User> response = userServiceFeign.getByUserId(Integer.valueOf(userId));
        User user = response.getData();
        return user;
    }

    /**
     * 是否认证
     * @param request
     * @return
     */
    public boolean isAuthentication(HttpServletRequest request){
        String token = getToken(request);
        if (token == null){
            return false;
        }
        // 用token换取userId
        String userId = (String) baseRedisService.getString(token);
        if (StringUtils.isEmpty(userId)){
            return false;
        }
        BaseResponse<User> response = userServiceFeign.getByUserId(Integer.valueOf(userId));
        User user = response.getData();
        if (StringUtils.isEmpty(user)){
            return false;
        }
        return true;
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(PublicConstant.COOKIE_TOKEN)){
                    String access_token = cookie.getValue();
                    return access_token;
                }
            }
        }
        return null;
    }
}
