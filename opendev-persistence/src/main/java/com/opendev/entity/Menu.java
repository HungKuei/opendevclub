package com.opendev.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 菜单表
 */

@Data
@NoArgsConstructor
public class Menu extends BaseEntity {

    /**
     * 菜单主键
     */
    private Integer menuId;

    /**
     * 父菜单主键
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型，0：菜单  `：业务按钮
     */
    private Integer menuType;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单url
     */
    private String menuUrl;

    /**
     * 菜单权限
     */
    private String authority;

    /**
     * 菜单的序号
     */
    private Integer sortNum;

    private List<Menu> childMenu;
}
