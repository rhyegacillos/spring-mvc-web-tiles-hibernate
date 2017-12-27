package com.springframework.web.services;

import com.springframework.web.model.Message;
import com.springframework.web.model.Offer;
import com.springframework.web.model.User;

import java.util.List;

public interface UsersService {

    void createUser(User user);

    boolean exists(String username);

    User getUser(String username);

    List<User> getAllUsers();

    List<Message> getMessages(String username);
}
