package com.opendev.api.service;

import com.opendev.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "微信服务接口")
public interface WeChatService {

    /**
     * 参数	描述  微信服务器验证地址接口
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     * @return
     */
    @GetMapping("/wechat/dispatcher")
    String dispatcherGet(@RequestParam("signature") String signature,
                         @RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce,
                         @RequestParam("echostr") String echostr);


    @ApiOperation(value = "验证微信手机号和注册码是否匹配")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "注册手机号"),
            @ApiImplicitParam(paramType = "query", name = "registCode", dataType = "String", required = true, value = "微信注册码")
    })
    @GetMapping("/wechat/validate")
    BaseResponse validateWeChatCode(@RequestParam("phone") String phone, @RequestParam("registCode") String registCode);



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
    @PostMapping("/wechat/dispatcher")
    void dispatcherGet(@RequestParam("request") HttpServletRequest request, @RequestParam("response") HttpServletResponse response) throws Exception;
}
