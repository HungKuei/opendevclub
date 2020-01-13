package com.opendev.api.service;

import com.opendev.base.BaseResponse;
import com.opendev.dto.UserInputDTO;
import com.opendev.dto.UserOutputDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    BaseResponse<UserOutputDTO> getByUserId(@PathVariable("userId") Long userId);

    /**
     * 添加用户
     * @param userInputDTO
     * @return
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    BaseResponse addUser(@RequestBody UserInputDTO userInputDTO);

    /**
     * 用户注册
     * @param userInputDTO
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    BaseResponse register(@RequestBody UserInputDTO userInputDTO);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户名"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "密码")
    })
    BaseResponse login(@RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "Integer", required = true, value = "页码"),
            @ApiImplicitParam(paramType = "query", name = "limit", dataType = "Integer", required = true, value = "每页行数")
    })
    @PostMapping("/list")
    BaseResponse getUserListByPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit);
}
