package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import com.opendev.constant.PublicConstant;
import com.opendev.dto.UserOutputDTO;
import com.opendev.feign.UserServiceFeign;
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
    public UserOutputDTO getCurrentUserInfo(HttpServletRequest request){
        String token = getToken(request);
        if (token == null){
            return null;
        }
        // 用token换取userId
        String userId = (String) baseRedisService.getString(token);
        if (StringUtils.isEmpty(userId)){
            return null;
        }
        BaseResponse<UserOutputDTO> response = userServiceFeign.getByUserId(Long.valueOf(userId));
        UserOutputDTO data = response.getData();
        return data;
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
        Long id = Long.valueOf(userId);
        BaseResponse<UserOutputDTO> response = userServiceFeign.getByUserId(id);
        UserOutputDTO data = response.getData();
        if (StringUtils.isEmpty(data)){
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
