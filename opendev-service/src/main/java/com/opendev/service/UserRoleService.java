package com.opendev.service;

import com.opendev.entity.Role;

import java.util.List;

public interface UserRoleService {

    List<Role> getRolesByUserId(Integer userId);

    Integer[] getRoleIdsByUserId(Integer userId);
}
