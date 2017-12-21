package com.springframework.web.test.tests;

import com.springframework.web.DAO.MessagesDAO;
import com.springframework.web.DAO.UsersDAO;
import com.springframework.web.model.Message;
import com.springframework.web.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;

import static org.junit.Assert.*;

@WebAppConfiguration("web")
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/java/com/springframework/web/config/offers-servlet.xml",
        "file:src/test/java/com/springframework/web/test/config/datasource.xml"})
public class MessagesDAOTest {

    @Autowired
    private MessagesDAO messagesDAO;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private DataSource dataSource;

    private User user1 = new User("rahye_07", "password", "rhye@gmail.com", true, "ROLE_ADMIN", "rhye");

    @Before
    public void setUp() throws Exception {
        JdbcTemplate jdbcTemplate =  new JdbcTemplate(dataSource);
        jdbcTemplate.execute("delete FROM  offers");
        jdbcTemplate.execute("delete FROM  messages");
        jdbcTemplate.execute("delete FROM  users");
    }

//    @Test
//    public void getMessages() throws Exception {
//    }
//
//    @Test
//    public void getMessages1() throws Exception {
//    }
//
//    @Test
//    public void getMessage() throws Exception {
//    }

    @Test
    public void saveOrUpdate() throws Exception {
        usersDAO.createUser(user1);
        Message message1  = new Message("Test Subject 1", "Test content 1", "Rhye", "rhye@gmail.com", user1.getUsername());
        messagesDAO.saveOrUpdate(message1);


    }

//    @Test
//    public void delete() throws Exception {
//    }

}