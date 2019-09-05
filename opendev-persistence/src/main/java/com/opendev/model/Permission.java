package com.opendev.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限菜单表
 */
@Data
@NoArgsConstructor
@Table(name = "t_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 7695943449476150570L;

    /**
     * 权限id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer permId;

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

    /**
     * 权限状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
