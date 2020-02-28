package com.opendev.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @description: 用户输出dto
 * @author: hungkuei
 */

@Data
@ApiModel(value = "用户返回参数")
public class UserOutputDTO implements Serializable {

    private static final long serialVersionUID = 7717837304991738653L;

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
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

    /**
     * 账号是否可以用 1 正常 0冻结
     */
    @ApiModelProperty(value = "账号是否可以用 1 正常 0冻结")
    private Integer status;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

}
