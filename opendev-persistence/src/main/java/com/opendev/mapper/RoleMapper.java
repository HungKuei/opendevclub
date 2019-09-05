package com.opendev.mapper;

import com.opendev.model.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRoleByUserId(@Param("userId") Integer userId);
}
