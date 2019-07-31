package com.opendev.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色表
 */
@Data
@NoArgsConstructor
public class Role extends BaseEntity {

    /**
     * 角色主键
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;


}
