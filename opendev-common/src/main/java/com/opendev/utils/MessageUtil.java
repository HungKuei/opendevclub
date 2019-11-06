package com.opendev.utils;

import com.alibaba.fastjson.JSONObject;
import com.opendev.base.TextMessage;

import java.util.Date;

public class MessageUtil {

    public static String msgJson(String interfaceType, String email){
        JSONObject msgJson = new JSONObject();
        JSONObject headerJson = new JSONObject();
        headerJson.put("interfaceType",interfaceType);
        JSONObject contentJson = new JSONObject();
        contentJson.put("email", email);
        msgJson.put("header", headerJson);
        msgJson.put("content", contentJson);
        return msgJson.toJSONString();
    }

    public static String setText(String content, String fromUserName, String toUserName){
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(content);
        textMessage.setMsgType("text");
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setFromUserName(fromUserName);
        textMessage.setToUserName(toUserName);
        // 将实体类转换成xml格式
        String msg = XmlUtil.messageToXml(textMessage);
        return msg;
    }
}
