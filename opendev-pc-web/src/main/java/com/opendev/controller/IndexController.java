package com.opendev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {

    private static final String INDEX = "index";
    private static final String REDIRECT_LOGIN = "redirect:/login";

    @GetMapping("/")
    public String index(HttpServletRequest request){
        if (isAuthentication(request)){
            return INDEX;
        }
        return REDIRECT_LOGIN;
    }
}
