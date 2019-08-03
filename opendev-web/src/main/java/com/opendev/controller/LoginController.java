package com.opendev.controller;

import com.opendev.base.BaseResponse;
import com.opendev.entity.User;
import com.opendev.service.RolePermissionService;
import com.opendev.service.UserRoleService;
import com.opendev.service.UserService;
import com.opendev.util.CommonUtil;
import com.opendev.util.JsonResult;
import com.wangfan.endecrypt.utils.EndecryptUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wf.jwtp.provider.Token;
import org.wf.jwtp.provider.TokenStore;

/**
 * 登录接口层
 */
@RestController
@RequestMapping("${api.version}/")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private TokenStore tokenStore;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form")
    })
    @PostMapping("login")
    public BaseResponse login(String username, String password){
        User user = userService.getUserByUsername(username);
        if (CommonUtil.isNull(user)){
            return JsonResult.error("账号不存在");
        }
        if (!user.getPassword().equals(EndecryptUtils.encrytMd5(password))){
            return JsonResult.error("密码错误");
        }
        if (user.getStatus() != 0){
            return JsonResult.error("账号被锁定");
        }
        String[] roles = CommonUtil.arrayToString(userRoleService.getRoleIdsByUserId(user.getId()));
        String[] permissions = CommonUtil.listToArray(rolePermissionService.getPermsByUserId(user.getId()));
        Token token = tokenStore.createNewToken(String.valueOf(user.getId()), permissions, roles);
        return JsonResult.success("登录成功").put("access_token", token.getAccessToken());
    }
}
