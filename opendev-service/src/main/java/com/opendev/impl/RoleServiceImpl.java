package com.opendev.impl;

import com.opendev.mapper.RoleMapper;
import com.opendev.model.Role;
import com.opendev.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleByUserId(Integer userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<Role> getRoleAll() {
        return roleMapper.selectAll();
    }

    @Override
    public boolean addRole(Role role) {
        return roleMapper.insertSelective(role) == 1;
    }

    @Override
    public boolean updateById(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role) == 1;
    }

    @Override
    public boolean deleteById(Integer roleId) {
        Role role = new Role();
        role.setRoleId(roleId);
        return roleMapper.deleteByPrimaryKey(role) == 1;
    }
}
