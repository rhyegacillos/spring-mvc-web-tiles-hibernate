package com.springframework.web.DAO;

import com.springframework.web.model.User;

import java.util.List;

public interface UsersDAO {

    void createUser(User user);

    boolean exists(String username);

    User getUser(String username);

    List<User> getAllUsers();
}
