package com.opendev.email.service;

import com.alibaba.fastjson.JSONObject;
import com.opendev.adapter.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 调用第三方发邮件
 */
@Slf4j
@Service
public class EmailService implements MessageAdapter {

    @Value("${msg.subject}")
    private String subject;

    @Value("${msg.text}")
    private String text;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMsg(JSONObject contentJson) {
        //发送邮件
        String email = contentJson.getString("email");

        if (StringUtils.isEmpty(email)){
            return;
        }
        log.info("#### 开始发送消息给邮箱：{}" , email);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email);
        //目标邮箱
        simpleMailMessage.setTo(email);
        //邮件标题
        simpleMailMessage.setSubject(subject);
        //邮件内容
        simpleMailMessage.setText(text.replace("{}", email));
        //发送邮件
        javaMailSender.send(simpleMailMessage);
        log.info("##### 发送邮件成功 -------------");
    }
}
