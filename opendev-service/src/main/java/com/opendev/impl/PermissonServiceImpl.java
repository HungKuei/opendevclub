package com.opendev.impl;

import com.opendev.mapper.PermissionMapper;
import com.opendev.model.Permission;
import com.opendev.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissonServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionByRoleId(Integer roleId) {
        return permissionMapper.selectPermissionByRoleId(roleId);
    }

    @Override
    public List<Permission> getPermsList() {
        return permissionMapper.selectAll();
    }
}
