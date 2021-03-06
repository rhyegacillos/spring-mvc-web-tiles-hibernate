package com.springframework.web.services;

import com.springframework.web.model.Offer;
import com.springframework.web.model.User;

import java.util.List;

public interface UsersService {

    void createUser(User user);

    boolean exists(String username);

    List<User> getAllUsers();
}
