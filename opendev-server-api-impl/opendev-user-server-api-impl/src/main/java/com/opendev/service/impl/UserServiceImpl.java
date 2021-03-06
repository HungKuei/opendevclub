package com.opendev.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.opendev.api.service.UserService;
import com.opendev.base.BaseResponse;
import com.opendev.base.BaseService;
import com.opendev.constant.PublicConstant;
import com.opendev.dto.UserInputDTO;
import com.opendev.dto.UserOutputDTO;
import com.opendev.enums.ResultStatusCode;
import com.opendev.feign.WeChatServiceFeign;
import com.opendev.mapper.UserMapper;
import com.opendev.mq.RegisterMailboxProducer;
import com.opendev.pojo.User;
import com.opendev.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@RestController
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;

    @Autowired
    private WeChatServiceFeign weChatServiceFeign;

    @Value("${messages.queue}")
    private String messagesQueue;

    @Override
    public BaseResponse<UserOutputDTO> getByUserId(@RequestParam("userId") Long userId) {
        User user = userMapper.selectById(userId);
        UserOutputDTO userOutputDTO = BeanUtil.doToDto(user, UserOutputDTO.class);
        if (userOutputDTO == null){
            return success("未查找到用户信息");
        }
        return success(userOutputDTO);
    }

    @Override
    @Transactional  //本地事务注解
    public BaseResponse addUser(@RequestBody UserInputDTO userInputDTO) {
        String username = userInputDTO.getUsername();
        if (StringUtils.isEmpty(username)){
            return error("用户名不能为空");
        }
        // 验证用户名是否存在
        User isExistUser = userMapper.selectByUsername(username);
        if (!StringUtils.isEmpty(isExistUser)){
            return error("该用户名已存在");
        }
        // 默认密码123456
        userInputDTO.setPassword(MD5Util.MD5("123456"));
        User user = BeanUtil.dtoToDo(userInputDTO, User.class);
        if (userMapper.insertSelective(user) != 1){
            return error("添加失败");
        }
        return success("添加成功");
    }

    @Override
    public BaseResponse register(@RequestBody UserInputDTO userInputDTO) {
        String username = userInputDTO.getUsername();
        String password = userInputDTO.getPassword();
        String email = userInputDTO.getEmail();
        // 参数校验
        if (StringUtils.isEmpty(username)){
            return error("用户名不能为空");
        }

        if (StringUtils.isEmpty(password)){
            return error("密码不能为空");
        }
        // 验证用户名是否存在
        User isExistUser = userMapper.selectByUsername(username);
        if (!StringUtils.isEmpty(isExistUser)){
            return error("该用户名已存在");
        }
        // 这里对用户密码进行盐值加密
        userInputDTO.setPassword(MD5Util.MD5(password));
        // 判断邮箱或手机号是否为空，发送邮箱或短信验证
        if (!StringUtils.isEmpty(email)){
            // 验证邮箱
            if (!ValidateUtil.isEmail(email)) {
                return error("请输入正确的邮箱地址");
            }
            String msgJson = MessageUtil.msgJson(PublicConstant.SMS_EMAIL, email);
            log.info("####将会员注册消息发送到消息平台:{}", msgJson);
            // 创建消息队列
            ActiveMQQueue messages_queue = new ActiveMQQueue(messagesQueue);
            // 发送消息
            registerMailboxProducer.sendMsg(messages_queue, msgJson);
        }
        User user = BeanUtil.dtoToDo(userInputDTO, User.class);
        user.setStatus(PublicConstant.STATUS_INVALID);
        user.setCreateTime(new Date());
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
        User isExistUser = userMapper.selectByUsername(username);
        if (StringUtils.isEmpty(isExistUser)){
            return error("用户不存在");
        }
        //密码校验
        String newPassword = MD5Util.MD5(password);
        if (!isExistUser.getPassword().equals(newPassword)){
            return error("账号或密码错误");
        }
        //生成token令牌
        String memberToken = TokenUtil.getMemberToken();
        //存入redis缓存
        baseRedisService.setString(memberToken, String.valueOf(isExistUser.getUserId()), PublicConstant.TOKEN_TIMEOUT);
        //返回token
        JSONObject token = new JSONObject();
        token.put("access_token", memberToken);
        return success(token);
    }

    @Override
    public BaseResponse<UserOutputDTO> ssoLogin(@RequestBody UserInputDTO userInputDTO) {
        // 验证参数
        String username = userInputDTO.getUsername();
        if (StringUtils.isEmpty(username)){
            return error(ResultStatusCode.NOT_FOUND.getCode(),"用户名不能为空");
        }
        String password = userInputDTO.getPassword();
        if (StringUtils.isEmpty(password)){
            return error(ResultStatusCode.NOT_FOUND.getCode(),"密码不能为空");
        }
        User isExistUser = userMapper.selectByUsername(username);
        if (StringUtils.isEmpty(isExistUser)){
            return error(ResultStatusCode.NOT_FOUND.getCode(),"用户不存在");
        }
        //密码校验
        String newPassword = MD5Util.MD5(password);
        if (!isExistUser.getPassword().equals(newPassword)){
            return error(ResultStatusCode.SYSTEM_ERR.getCode(),"账号或密码错误");
        }
        return success(BeanUtil.doToDto(isExistUser, UserOutputDTO.class));
    }

    @Override
    public BaseResponse getUserListByPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        page = (page - 1) * limit;
        List<User> userList = userMapper.selectByPageLimit(page, limit);
        List<UserOutputDTO> userOutputDTOS = new ArrayList<>();
        for (User user : userList){
            userOutputDTOS.add(BeanUtil.doToDto(user, UserOutputDTO.class));
        }
        Long count = userMapper.count();
        return success(userOutputDTOS, count);
    }
}
