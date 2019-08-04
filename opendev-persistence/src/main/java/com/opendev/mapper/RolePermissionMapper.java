package com.opendev.mapper;

import com.opendev.model.RolePermission;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<String> selectPermsByRoleIds(List<Integer> roleIds);
}
