package com.opendev.service;

import com.opendev.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesByUserId(Integer userId);
}
