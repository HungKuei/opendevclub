package com.opendev.service;

import com.opendev.model.User;

public interface UserService {

    User getUserByUsername(String username);
}
