package com.opendev.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


/**
 * 用户表
 * @author hungkuei
 */
@Data
@NoArgsConstructor
public class User extends BaseEntity {

    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
    private String sex;

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
    private Date birthday;

    /**
     * 状态值（1：正常，2：禁用，3：删除）
     */
    private Integer status;

    /**
     * 权限
     */
    private List<Jurisdiction> jurisdictions;

    /**
     * 角色
     */
    private List<Role> roles;

}
