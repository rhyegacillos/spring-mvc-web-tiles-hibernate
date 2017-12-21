package com.springframework.web.DAO;

import com.springframework.web.model.Message;
import com.springframework.web.model.Offer;

import java.util.List;


public interface MessagesDAO {

    List<Message> getMessages(String username);
    List<Message> getMessages();
    Message getMessage(int id);
    void saveOrUpdate(Message message);
    void delete(int id);
}
