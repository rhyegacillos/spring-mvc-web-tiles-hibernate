package com.springframework.web.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.springframework.web.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsersDAOImpl implements UsersDAO {

    private PasswordEncoder passwordEncoder;
    private SessionFactory sessionFactory;

    @Autowired
    public UsersDAOImpl(PasswordEncoder passwordEncoder, SessionFactory sessionFactory) {
        this.passwordEncoder = passwordEncoder;
        this.sessionFactory = sessionFactory;
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session().save(user);
    }

    public boolean exists(String username) {

        User user = getUser(username);

        return user != null;
    }

    @Override
    public User getUser(String username) {
        return session().find(User.class, username);
    }

    @Override
    public List<User> getAllUsers() {

        return session().createQuery("from users", User.class).getResultList();
    }
}
