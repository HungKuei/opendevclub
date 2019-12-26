package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.feign.BbsServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private BbsServiceFeign bbsServiceFeign;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/getLatestBbs")
    public BaseResponse getLatestBbs(Long userId, int offset, int limit){
        return bbsServiceFeign.getLatestBbs(userId, offset, limit);
    }
}
