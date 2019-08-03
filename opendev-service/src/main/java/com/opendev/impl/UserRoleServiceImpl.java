package com.opendev.impl;

import com.opendev.entity.Role;
import com.opendev.mapper.UserRoleMapper;
import com.opendev.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getRolesByUserId(Integer userId) {
        return userRoleMapper.selectRolesByUserId(userId);
    }

    @Override
    public Integer[] getRoleIdsByUserId(Integer userId) {
        List<Integer> roleIds = userRoleMapper.selectRoleIdsByUserId(userId);
        Integer[] roles = new Integer[roleIds.size()];
        for (int i = 0; i < roleIds.size(); i++){
            roles[i] = roleIds.get(i);
        }
        return roles;
    }
}
