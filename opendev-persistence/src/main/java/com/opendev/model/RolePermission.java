package com.opendev.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermission {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限
     */
    private Integer permId;
}
