package com.opendev.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户表
 * @author hungkuei
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -1157583928243692538L;

    private Long userId;

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 是电话号码，也是账号（登录用）
     */
    private String mobile;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮箱是否验证，0未验证，1已验证
     */
    private Integer emailVerified;

    /**
     * 真实姓名
     */

    private String trueName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    /**
     * 账号状态
     */
    private Integer status;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 上次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;
}
