package com.springframework.web.DAO;

import com.springframework.web.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class MessagesDAOImpl implements MessagesDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public MessagesDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Message> getMessages(String username) {
        return session().createQuery("from message where users.username=:username", Message.class).getResultList();
    }

    @Override
    public List<Message> getMessages() {
        return session().createQuery("from message ", Message.class).getResultList();
    }

    @Override
    public Message getMessage(int id) {
        Query<Message> query = session().createQuery("from message p where p.id= :id");
        query.setParameter("id", id);

        return query.uniqueResult();
    }

    @Override
    public void saveOrUpdate(Message message) {

        session().saveOrUpdate(message);

    }

    @Override
    public void delete(int id) {

        Message message = getMessage(id);
        session().delete(message);

    }
}
