package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.constant.PublicConstant;
import com.opendev.feign.PayServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

@Controller
public class PayController {

    @Autowired
    private PayServiceFeign payServiceFeign;

    @RequestMapping("/alipay")
    public void alipay(@RequestParam("payToken") String payToken, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        // 参数验证
        if (StringUtils.isEmpty(payToken)){
            return;
        }
        // 调用支付服务接口 获取支付宝html元素
        BaseResponse resPayToken = payServiceFeign.getPayToken(payToken);
        if (!resPayToken.getCode().equals(PublicConstant.SUCCESS_CODE)){
            String msg = resPayToken.getMsg();
            writer.println(msg);
            return;
        }
        // 返回可以执行的html元素给客户端
        LinkedHashMap dataMap = (LinkedHashMap) resPayToken.getData();
        String payHtml = (String) dataMap.get("payHtml");
        // 页面上进行渲染
        writer.println(payHtml);
        writer.close();
    }
}
