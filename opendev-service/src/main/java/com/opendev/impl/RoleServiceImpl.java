package com.opendev.impl;

import com.opendev.mapper.RoleMapper;
import com.opendev.model.Role;
import com.opendev.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> getRolesByUserId(Integer userId) {
        return null;
    }
}
