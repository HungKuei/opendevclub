package com.opendev.mapper;

import com.opendev.model.Role;
import com.opendev.model.UserRole;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Integer> selectRoleIdsByUserId(Integer userId);

    List<Role> selectRolesByUserId(Integer userId);
}
