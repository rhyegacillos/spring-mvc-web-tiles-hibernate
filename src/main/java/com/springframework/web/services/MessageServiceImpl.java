package com.springframework.web.services;

import com.springframework.web.DAO.MessagesDAO;
import com.springframework.web.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

    private MessagesDAO messagesDAO;

    @Autowired
    public MessageServiceImpl(MessagesDAO messagesDAO) {
        this.messagesDAO = messagesDAO;
    }

    @Transactional
    @Override
    public void sendMessage(Message message) {
        messagesDAO.saveOrUpdate(message);
    }

}
