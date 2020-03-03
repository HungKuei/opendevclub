package com.opendev.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号码，也是账号（登录用）
     */
    private String mobile;

    /**
     * 用户邮箱
     */
    private String email;


    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 用户出生日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date birthday;

    /**
     * 用户关联 QQ 开放ID
     */
    private String qqOpenId;

    /**
     * 用户关联 微信 开放ID
     */
    private String wxOpenId;

    /**
     * 账号状态是否可以用 1 正常 0冻结
     */
    private Integer status;

    /**
     * 注册时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
