package com.opendev.service;

import com.opendev.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoleByUserId(Integer userId);

    List<Role> getRoleAll();

    boolean addRole(Role role);

    boolean updateById(Role role);

}
