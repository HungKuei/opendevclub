package com.opendev.service;


import java.util.List;

public interface RolePermissionService {

    List<String> getPermsByUserId(Integer userId);
}
