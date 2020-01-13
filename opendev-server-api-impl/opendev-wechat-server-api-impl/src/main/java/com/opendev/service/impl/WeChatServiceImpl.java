package com.opendev.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.opendev.api.service.WeChatService;
import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import com.opendev.constant.PublicConstant;
import com.opendev.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
@RestController
public class WeChatServiceImpl extends BaseService implements WeChatService {

    private static final String REQUEST_URL = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String dispatcherGet(@RequestParam("signature") String signature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestParam("echostr") String echostr) {
        // 验证参数
        boolean isCheck = CheckUtil.checkSignature(signature, timestamp, nonce);
        // 验证成功返回随机字符串
        if (!isCheck){
            return null;
        }
        return echostr;
    }

    @Override
    public BaseResponse validateWeChatCode(@RequestParam("phone") String phone, @RequestParam("registCode") String registCode) {
        // 参数验空
        if (StringUtils.isEmpty(phone)){
            error("手机号码为空");
        }
        if (StringUtils.isEmpty(registCode)){
            error("注册码为空");
        }
        // 查询注册码并进行比对
        String wechatCodeKey = PublicConstant.WECHAT_CODE_KEY + phone;
        String redisCode = redisUtil.getString(wechatCodeKey);
        if (StringUtils.isEmpty(redisCode)){
            return error("注册码已过期");
        }
        if (!redisCode.equals(registCode)){
            return error("注册码不正确");
        }
        // 删除注册码
        redisUtil.delKey(wechatCodeKey);
        return success();
    }

    @Override
    public void dispatcherGet(@RequestParam("request") HttpServletRequest request, @RequestParam("response") HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // 将XML转换成Map格式
        Map<String, String> stringMap = XmlUtil.parseXml(request);
        log.info("##### 收到微信消息：{}" , stringMap.toString());
        // 判断消息类型
        String msgType = stringMap.get("MsgType");
        // 响应
        PrintWriter writer = response.getWriter();
        // 如果是文本，返回到微信服务器
        switch(msgType){
            case "text":
                // 开发者微信公众号
                String toUserName = stringMap.get("ToUserName");
                // 消息来自微信号
                String fromUserName = stringMap.get("FromUserName");
                // 消息内容
                String content = stringMap.get("Content");
                String msg = null;
                String result = HttpClientUtil.doGet(REQUEST_URL + content);
                JSONObject msgJson = JSONObject.parseObject(result);
                Integer resultCode = msgJson.getInteger("result");
                if (resultCode.equals(PublicConstant.STATUS_INVALID)){
                    String resultContent = msgJson.getString("content");
                    msg = MessageUtil.setText(resultContent, fromUserName, toUserName);
                }else{
                    msg = MessageUtil.setText("服务器繁忙，请稍后重试", fromUserName, toUserName);
                }
                log.info("##### 自动回复消息：{}" ,msg);
                writer.println(msg);
                break;
            default:
                break;
        }
        writer.close();
    }
}
