package com.opendev.controller;

import com.xxl.sso.core.conf.Conf;
import com.xxl.sso.core.user.XxlSsoUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {

    private static final String INDEX = "index";
    private static final String CONSOLE = "index/console";
    private static final String MESSAGE = "tpl/message";
    private static final String THEME = "tpl/theme";
    private static final String REDIRECT_LOGIN = "redirect:/login";

    /*@GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        if (isAuthentication(request)){
            UserOutputDTO userInfo = getCurrentUserInfo(request);
            model.addAttribute("user", userInfo);
            return INDEX;
        }
        return REDIRECT_LOGIN;
    }*/

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request){
        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        model.addAttribute("user", xxlUser);
        return INDEX;
    }

    /*工作台*/
    @GetMapping("/console")
    public String workdest(){
        return CONSOLE;
    }

    /*消息*/
    @GetMapping("/message")
    public String message(){
        return MESSAGE;
    }

    /*主题*/
    @GetMapping("/theme")
    public String theme(){
        return THEME;
    }
}
