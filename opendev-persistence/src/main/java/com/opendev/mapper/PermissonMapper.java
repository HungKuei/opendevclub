package com.opendev.mapper;

import com.opendev.model.Permission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface PermissonMapper extends BaseMapper<Permission> {

    List<Permission> selectPermissionByRoleId(@Param("roleId") Integer roleId);
}
