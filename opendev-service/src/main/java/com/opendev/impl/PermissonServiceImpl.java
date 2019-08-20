package com.opendev.impl;

import com.opendev.model.Permission;
import com.opendev.service.PermissonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissonServiceImpl implements PermissonService {
    @Override
    public List<Permission> getPermissionsByRoleId(Integer roleId) {
        return null;
    }
}
