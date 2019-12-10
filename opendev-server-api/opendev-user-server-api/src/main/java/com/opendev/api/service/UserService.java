package com.opendev.api.service;

import com.opendev.base.BaseResponse;
import com.opendev.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(tags = "用户服务接口")
@RequestMapping("/user")
public interface UserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据用户id查询用户信息")
    @GetMapping("/get/{userId}")
    BaseResponse<User> getByUserId(@PathVariable("userId") Integer userId);

    /**
     * 添加用户
     * @param user
     * @return
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    BaseResponse addUser(@RequestBody User user);

    /**
     * 用户注册
     * @param user
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    BaseResponse register(@RequestBody User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    BaseResponse login(@RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/list")
    BaseResponse getUserListByPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit);
}
