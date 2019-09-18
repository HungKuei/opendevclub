package com.opendev.service;

import com.opendev.model.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getPermissionByRoleId(Integer roleId);

    List<Permission> getPermsList();

    boolean addPermission(Permission permission);
}
