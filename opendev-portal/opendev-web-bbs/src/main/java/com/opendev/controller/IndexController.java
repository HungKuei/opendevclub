package com.opendev.controller;

import com.xxl.sso.core.conf.Conf;
import com.xxl.sso.core.user.XxlSsoUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request){
        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        model.addAttribute("user", xxlUser);
        return "index";
    }
}
