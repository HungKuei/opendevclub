package com.opendev.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.opendev.api.service.WeChatService;
import com.opendev.constant.PublicConstant;
import com.opendev.utils.CheckUtil;
import com.opendev.utils.HttpClientUtil;
import com.opendev.utils.MessageUtil;
import com.opendev.utils.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
@RestController
public class WeChatServiceImpl implements WeChatService {

    private static final String REQUEST_URL = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";

    @Override
    public String dispatcherGet(String signature, String timestamp, String nonce, String echostr) {
        // 验证参数
        boolean isCheck = CheckUtil.checkSignature(signature, timestamp, nonce);
        // 验证成功返回随机字符串
        if (!isCheck){
            return null;
        }
        return echostr;
    }

    @Override
    public void dispatcherGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
