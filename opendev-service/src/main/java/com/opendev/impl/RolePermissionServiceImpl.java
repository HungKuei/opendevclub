package com.opendev.impl;

import com.opendev.mapper.RolePermissionMapper;
import com.opendev.mapper.UserRoleMapper;
import com.opendev.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> getPermsByUserId(Integer userId) {
        List<Integer> roleIds = userRoleMapper.selectRoleIdsByUserId(userId);
        return rolePermissionMapper.selectPermsByRoleIds(roleIds);
    }
}
