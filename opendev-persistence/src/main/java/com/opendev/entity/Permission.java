package com.opendev.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限菜单表
 */
@Data
@NoArgsConstructor
public class Permission extends BaseEntity {


    /**
     * 父级主键
     */
    private Integer parentId;

    /**
     * 模块名称
     */
    private String menuName;

    /**
     * 权限描述
     */
    private String permDesc;

    /**
     * 菜单类型，0：菜单  `：业务按钮
     */
    private Integer menuType;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 权限url
     */
    private String permUrl;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单的序号
     */
    private Integer sortNum;

}
