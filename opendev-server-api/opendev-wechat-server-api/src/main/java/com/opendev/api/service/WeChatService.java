package com.opendev.api.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/wechat")
public interface WeChatService {

    /**
     * 参数	描述  微信服务器验证地址接口
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     * @return
     */
    @GetMapping("/dispatcher")
    String dispatcherGet(String signature, String timestamp, String nonce, String echostr);


    /**
     * 参数	描述    微信推送
     * ToUserName	开发者微信号
     * FromUserName	发送方帐号（一个OpenID）
     * CreateTime	消息创建时间 （整型）
     * MsgType	消息类型，文本为text
     * Content	文本消息内容
     * MsgId	消息id，64位整型
     * @param request
     * @return
     */
    @PostMapping("/dispatcher")
    void dispatcherGet(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
