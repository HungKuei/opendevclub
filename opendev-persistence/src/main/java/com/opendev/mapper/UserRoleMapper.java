package com.opendev.mapper;

import com.opendev.entity.Role;
import com.opendev.entity.UserRole;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Integer> selectRoleIdsByUserId(Integer userId);

    List<Role> selectRolesByUserId(Integer userId);
}
