package com.springframework.web.services;

import com.springframework.web.DAO.MessagesDAO;
import com.springframework.web.DAO.UsersDAO;
import com.springframework.web.model.Message;
import com.springframework.web.model.Offer;
import com.springframework.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersDAO usersDAO;
    private MessagesDAO messagesDAO;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersDAOImpl") UsersDAO usersDAO, @Qualifier("messagesDAOImpl") MessagesDAO messagesDAO) {
        this.usersDAO = usersDAO;
        this.messagesDAO = messagesDAO;
    }

    @Transactional
    public void createUser(User user) {
        usersDAO.createUser(user);
    }

    @Transactional
    public boolean exists(String username) {
        return usersDAO.exists(username);
    }

    @Transactional
    @Override
    public User getUser(String username) {
        if(username != null){
            return usersDAO.getUser(username);
        } else  {
            return null;
        }
    }

    @Override
    @Secured("ROLE_ADMIN")
    @Transactional
    public List<User> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    @Transactional
    @Override
    public List<Message> getMessages(String username) {
        return messagesDAO.getMessages(username);
    }
}
