package com.opendev.api.service;

import com.opendev.base.BaseResponse;
import com.opendev.model.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("/get/{userId}")
    BaseResponse<User> getByUserId(@PathVariable("userId") Integer userId);

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    BaseResponse addUser(@RequestBody User user);

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    BaseResponse register(@RequestBody User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    BaseResponse login(@RequestParam("username") String username, @RequestParam("password") String password);
}
