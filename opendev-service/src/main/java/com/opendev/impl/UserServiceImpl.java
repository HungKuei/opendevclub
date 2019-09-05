package com.opendev.impl;

import com.github.pagehelper.PageHelper;
import com.opendev.model.User;
import com.opendev.mapper.UserMapper;
import com.opendev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserId(Integer userId) {
        return userMapper.selectByUserId(userId);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insertSelective(user) == 1;
    }

    @Override
    public boolean updateByUserId(User user) {
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> getUsersByPages(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<User> userList = userMapper.selectAll();
        return userList;
    }
}
