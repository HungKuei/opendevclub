package com.opendev.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户登陆请求参数")
public class UserLoginInputDTO implements Serializable {

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 登陆类型 PC端 移动端 安卓 IOS 平板
     */
    @ApiModelProperty(value = "登陆类型")
    private String loginType;

}
