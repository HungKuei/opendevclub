package com.opendev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final String PASSWORD = "tpl/password";

    @GetMapping("/password")
    public String getUserInfo(HttpServletRequest request){
        return PASSWORD;
    }
}
