package com.opendev.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleToJurisdiction {

    /**
     * 角色id
     */
    private String roleId;
    /**
     * 权限
     */
    private String authority;
}
