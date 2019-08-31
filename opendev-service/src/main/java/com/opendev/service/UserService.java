package com.opendev.service;

import com.opendev.model.User;

import java.util.List;

public interface UserService {

    User getUserById(Integer id);

    boolean addUser(User user);

    boolean updateUser(User user);

    User getUserByUsername(String username);

    List<User> getUsersByPages(Integer page, Integer rows);
}
