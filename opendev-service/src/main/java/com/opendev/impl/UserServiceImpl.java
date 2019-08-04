package com.opendev.impl;

import com.opendev.model.User;
import com.opendev.mapper.UserMapper;
import com.opendev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
