package com.opendev.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.opendev.api.service.UserService;
import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import com.opendev.constant.PublicConstant;
import com.opendev.mapper.UserMapper;
import com.opendev.model.User;
import com.opendev.mq.RegisterMailboxProducer;
import com.opendev.utils.MD5Util;
import com.opendev.utils.MessageUtil;
import com.opendev.utils.TokenUtil;
import com.opendev.utils.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;

    @Value("${messages.queue}")
    private String messagesQueue;

    @Override
    public BaseResponse<User> getByUserId(@PathVariable("userId") Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null){
            return success("未查找到用户信息");
        }
        return success(user);
    }

    @Override
    public BaseResponse addUser(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUsername())){
            return error("用户名不能为空");
        }
        // 验证用户名是否存在
        User userDb = userMapper.selectByUsername(user.getUsername());
        if (!StringUtils.isEmpty(userDb)){
            return error("该用户名已存在");
        }
        // 默认密码123456
        user.setPassword(MD5Util.MD5("123456"));
        // 默认状态为正常
        user.setStatus(PublicConstant.STATUS_INVALID);
        if (userMapper.insert(user) != 1){
            return error("添加失败");
        }
        return success("添加成功");
    }

    @Override
    public BaseResponse register(@RequestBody User user) {
        // 参数校验
        if (StringUtils.isEmpty(user.getUsername())){
            return error("用户名不能为空");
        }
        // 验证用户名是否存在
        User userDb = userMapper.selectByUsername(user.getUsername());
        if (!StringUtils.isEmpty(userDb)){
            return error("该用户名已存在");
        }
        if (StringUtils.isEmpty(user.getPassword())){
            return error("密码不能为空");
        }
        user.setStatus(PublicConstant.STATUS_INVALID);
        // 这里对用户密码进行盐值加密
        user.setPassword(MD5Util.MD5(user.getPassword()));
        // 判断邮箱或手机号是否为空，发送邮箱或短信验证
        if (!StringUtils.isEmpty(user.getEmail())){
            // 验证邮箱
            if (!ValidateUtil.isEmail(user.getEmail())) {
                return error("请输入正确的邮箱地址");
            }
            String msgJson = MessageUtil.msgJson(PublicConstant.SMS_EMAIL, user.getEmail());
            log.info("####将会员注册消息发送到消息平台:{}", msgJson);
            // 创建消息队列
            ActiveMQQueue messages_queue = new ActiveMQQueue(messagesQueue);
            // 发送消息
            registerMailboxProducer.sendMsg(messages_queue, msgJson);
        }
        if (userMapper.insert(user) != 1) {
            return error("注册失败");
        }
        // 注册成功
        return success("注册成功");
    }

    @Override
    public BaseResponse login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 验证参数
        if (StringUtils.isEmpty(username)){
            return error("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)){
            return error("密码不能为空");
        }
        User userDb = userMapper.selectByUsername(username);
        if (StringUtils.isEmpty(userDb)){
            return error("用户不存在");
        }
        //密码校验
        String newPassword = MD5Util.MD5(password);
        if (!userDb.getPassword().equals(newPassword)){
            return error("账号或密码错误");
        }
        //生成token令牌
        String memberToken = TokenUtil.getMemberToken();
        //存入redis缓存
        baseRedisService.setString(memberToken, String.valueOf(userDb.getId()), PublicConstant.TOKEN_TIMEOUT);
        //返回token
        JSONObject token = new JSONObject();
        token.put("access_token", memberToken);
        return success(token);
    }
}
