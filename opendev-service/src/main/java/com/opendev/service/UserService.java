package com.opendev.service;

import com.opendev.entity.User;

public interface UserService {

    User getUserByUsername(String username);
}
