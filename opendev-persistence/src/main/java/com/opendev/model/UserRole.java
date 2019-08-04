package com.opendev.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRole{

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;
}
