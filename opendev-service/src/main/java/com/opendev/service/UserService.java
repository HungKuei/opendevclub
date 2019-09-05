package com.opendev.service;

import com.opendev.model.User;

import java.util.List;

public interface UserService {

    User getUserByUserId(Integer userId);

    boolean addUser(User user);

    boolean updateByUserId(User user);

    User getUserByUsername(String username);

    List<User> getUsersByPages(Integer page, Integer rows);
}
