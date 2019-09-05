package com.opendev.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Table(name = "t_role_permission")
public class RolePermission {

    /**
     * 主键
     */
    @Id
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
