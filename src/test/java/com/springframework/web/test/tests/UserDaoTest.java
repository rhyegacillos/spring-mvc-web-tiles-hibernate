package com.springframework.web.test.tests;

import com.springframework.web.DAO.UsersDAO;
import com.springframework.web.model.User;
import org.junit.Assert;
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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@WebAppConfiguration("web")
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/java/com/springframework/web/config/offers-servlet.xml",
                                    "file:src/test/java/com/springframework/web/test/config/datasource.xml"})
public class UserDaoTest {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private DataSource dataSource;

    private User user1 = new User("rahye_07", "password", "rhye@gmail.com", true, "ROLE_ADMIN", "rhye");
    private User user2 = new User("rahye_09", "password", "rhye9@gmail.com", true, "ROLE_USER", "rhye1");
    private User user3 = new User("rahye_11", "password", "rhye11@gmail.com", true, "ROLE_ADMIN", "rhye2");
    private User user4 = new User("rahye_02", "password", "rhye2@gmail.com", true, "ROLE_USER", "rhye3");


    @Before
    public void init() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("delete FROM  offers");
        jdbcTemplate.execute("delete FROM  users");

    }

    @Test
    public void testCreateUser() {
        usersDAO.createUser(user1);

        List<User> users = usersDAO.getAllUsers();

        assertEquals(user1.toString(), users.get(0).toString());

        usersDAO.createUser(user2);
        usersDAO.createUser(user3);
        usersDAO.createUser(user4);

        List<User> users2 = usersDAO.getAllUsers();

        assertEquals(4, users2.size());
    }

    @Test
    public void testUserExist() {
        usersDAO.createUser(user1);

        assertTrue(usersDAO.exists(user1.getUsername()));
        assertFalse(usersDAO.exists("sample"));
    }

    @Test
    public void testRetrieveUsers() {
        usersDAO.createUser(user1);
        usersDAO.createUser(user2);
        usersDAO.createUser(user3);
        usersDAO.createUser(user4);

        assertEquals(4, usersDAO.getAllUsers().size());
    }
}
