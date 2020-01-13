package com.opendev.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @description: 用户输入dto
 * @author: hungkuei
 */

@Data
@ApiModel(value = "用户输入信息实体类")
public class UserInputDTO implements Serializable {

    private static final long serialVersionUID = 3357819467594936116L;

    /**
     * userid
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 性别 0 男 1女
     */
    @ApiModelProperty(value = "用户性别")
    private Integer sex;

    /**
     * 用户生日
     */
    @ApiModelProperty(value = "用户生日")
    private Date birthday;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = " 用户头像")
    private String avatar;

    /**
     * 用户关联 QQ 开放ID
     */
    @ApiModelProperty(value = "用户关联 QQ 开放ID")
    private String qqOpenId;

    /**
     * 用户关联 微信 开放ID
     */
    @ApiModelProperty(value = "用户关联 微信 开放ID")
    private String wxOpenId;
}
