package com.opendev.service;

import com.opendev.model.Permission;

import java.util.List;

public interface PermissonService {

    List<Permission> getPermissionByRoleId(Integer roleId);
}
