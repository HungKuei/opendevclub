package com.opendev.mapper;

import com.opendev.entity.RolePermission;

import java.util.List;

public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<String> selectPermsByRoleIds(List<Integer> roleIds);
}
