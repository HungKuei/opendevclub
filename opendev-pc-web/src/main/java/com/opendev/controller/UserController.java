package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.feign.UserServiceFeign;
import com.opendev.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserServiceFeign userServiceFeign;

    private static final String PASSWORD = "tpl/password";
    private static final String USER_LIST = "user/user";

    @GetMapping("/password")
    public String getUserInfo(HttpServletRequest request){
        return PASSWORD;
    }

    @GetMapping("/list")
    public String userList(){
        return USER_LIST;
    }

    @PostMapping("/list")
    public BaseResponse userList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return userServiceFeign.getUserListByPage(page, limit);
    }
}
