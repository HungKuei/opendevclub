package com.opendev.impl;

import com.opendev.mapper.PermissonMapper;
import com.opendev.model.Permission;
import com.opendev.service.PermissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissonServiceImpl implements PermissonService {

    @Autowired
    private PermissonMapper permissonMapper;

    @Override
    public List<Permission> getPermissionByRoleId(Integer roleId) {
        return permissonMapper.selectPermissionByRoleId(roleId);
    }
}
