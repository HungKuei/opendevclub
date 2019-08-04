package com.opendev.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色表
 */
@Data
@NoArgsConstructor
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

}
