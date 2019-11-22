package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.constant.PublicConstant;
import com.opendev.feign.PaymentServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class PayController {

    @Autowired
    private PaymentServiceFeign payServiceFeign;

    private static final String PAY_SUCCESS = "pay_success";

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

    /**
     * 同步回调
     * @param request
     * @param response
     */
    @RequestMapping("/back/synNotify")
    public void synCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取支付宝GET过来反馈信息
        Map<String,String[]> requestParams = request.getParameterMap();
        Map<String, String> params = getParams(requestParams);
        PrintWriter writer = response.getWriter();
        // 调用支付服务回调接口
        BaseResponse baseResponse = payServiceFeign.synNotify(params);
        if (!baseResponse.getCode().equals(PublicConstant.SUCCESS_CODE)) {
            return;
        }
        LinkedHashMap data = (LinkedHashMap) baseResponse.getData();
        String htmlFrom = "<form name='punchout_form'"
                + " method='post' action='http://127.0.0.1/back/synSuccessPage' >"
                + "<input type='hidden' name='outTradeNo' value='" + data.get("outTradeNo") + "'>"
                + "<input type='hidden' name='tradeNo' value='" + data.get("tradeNo") + "'>"
                + "<input type='hidden' name='totalAmount' value='" + data.get("totalAmount") + "'>"
                + "<input type='submit' value='立即支付' style='display:none'>"
                + "</form><script>document.forms[0].submit();" + "</script>";
        writer.println(htmlFrom);
        writer.close();
    }

    // 同步回调,解决隐藏参数
    @PostMapping("/back/synSuccessPage")
    public String synSuccessPage(HttpServletRequest request, String outTradeNo, String tradeNo, String totalAmount) {
        request.setAttribute("outTradeNo", outTradeNo);
        request.setAttribute("tradeNo", tradeNo);
        request.setAttribute("totalAmount", totalAmount);
        return PAY_SUCCESS;
    }


    /**
     * 异步回调
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/back/asynNotify")
    @ResponseBody
    public String asynCallBack(HttpServletRequest request) throws IOException {
        //获取支付宝POST过来反馈信息
        Map<String,String[]> requestParams = request.getParameterMap();
        Map<String, String> params = getParams(requestParams);
        return payServiceFeign.asynNotify(params);
    }


    // 获取支付宝回调反馈信息
    private Map<String, String> getParams(Map<String,String[]> requestParams) throws IOException {
        Map<String,String> params = new HashMap<>();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }
}
