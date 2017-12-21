package com.springframework.web.test.tests;

import com.springframework.web.DAO.OffersDAO;
import com.springframework.web.DAO.UsersDAO;
import com.springframework.web.model.Offer;
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

import static org.junit.Assert.*;

@WebAppConfiguration("web")
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/java/com/springframework/web/config/offers-servlet.xml",
        "file:src/test/java/com/springframework/web/test/config/datasource.xml"})
public class OffersDaoTest {

    @Autowired
    private OffersDAO offersDAO;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private DataSource dataSource;

    private User user1 = new User("rahye_07", "password", "rhye@gmail.com", true, "ROLE_ADMIN", "rhye");
    private User user2 = new User("rahye_09", "password", "rhye9@gmail.com", true, "ROLE_USER", "rhye1");
    private User user3 = new User("rahye_11", "password", "rhye11@gmail.com", true, "ROLE_ADMIN", "rhye2");
    private User user4 = new User("rahye_02", "password", "rhye2@gmail.com", false, "ROLE_USER", "rhye3");

    private Offer offer1 = new Offer("This is Offer 1 Sample", user1);
    private Offer offer2 = new Offer("This is Offer 2 Sample", user1);
    private Offer offer3 = new Offer("This is Offer 3 Sample", user2);
    private Offer offer4 = new Offer("This is Offer 4 Sample", user3);
    private Offer offer5 = new Offer("This is Offer 5 Sample", user3);
    private Offer offer6 = new Offer("This is Offer 6 Sample", user3);
    private Offer offer7 = new Offer("This is Offer 7 Sample", user4);


    @Before
    public void init() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("delete FROM  offers");
        jdbcTemplate.execute("delete FROM  messages");
        jdbcTemplate.execute("delete FROM  users");

    }

    @Test
    public void testCreateAndRetrieveOffers() {
        usersDAO.createUser(user1);
        usersDAO.createUser(user2);
        usersDAO.createUser(user3);
        usersDAO.createUser(user4);

        offersDAO.saveOrUpdate(offer1);

        List<Offer> offers1 = offersDAO.getOffers();
        assertEquals("Should be 1 offer", 1, offers1.size());
        assertEquals(offer1.toString(), offers1.get(0).toString());

        offersDAO.saveOrUpdate(offer2);
        offersDAO.saveOrUpdate(offer3);
        offersDAO.saveOrUpdate(offer4);
        offersDAO.saveOrUpdate(offer5);
        offersDAO.saveOrUpdate(offer6);
        offersDAO.saveOrUpdate(offer7);

        List<Offer> offers2 = offersDAO.getOffers();
        assertEquals("Should be 6 offers for enabled users", 6, offers2.size());
    }

    @Test
    public void testRetrieveOffersByUsername() {
        usersDAO.createUser(user1);
        usersDAO.createUser(user2);
        usersDAO.createUser(user3);
        usersDAO.createUser(user4);

        offersDAO.saveOrUpdate(offer1);
        offersDAO.saveOrUpdate(offer2);
        offersDAO.saveOrUpdate(offer3);
        offersDAO.saveOrUpdate(offer4);
        offersDAO.saveOrUpdate(offer5);
        offersDAO.saveOrUpdate(offer6);
        offersDAO.saveOrUpdate(offer7);

        List<Offer> offers = offersDAO.getOffers(user3.getUsername());

        assertEquals("There should 3 offers for user3", 3, offers.size());

        List<Offer> offers1 = offersDAO.getOffers("test");

        assertEquals("Should be 0 since for username not exist", 0, offers1.size());

    }

    @Test
    public void testGetOfferById() {
        usersDAO.createUser(user1);
        usersDAO.createUser(user2);
        usersDAO.createUser(user3);
        usersDAO.createUser(user4);

        offersDAO.saveOrUpdate(offer1);
        offersDAO.saveOrUpdate(offer2);
        offersDAO.saveOrUpdate(offer3);
        offersDAO.saveOrUpdate(offer4);
        offersDAO.saveOrUpdate(offer5);
        offersDAO.saveOrUpdate(offer6);
        offersDAO.saveOrUpdate(offer7);

        Offer retrievedOfferWithDisabledUser = offersDAO.getOffer(offer7.getId());

        assertNull(retrievedOfferWithDisabledUser);

        Offer retrievedOfferWithEnableUser = offersDAO.getOffer(offer2.getId());

        assertNotNull(retrievedOfferWithEnableUser);

    }

    @Test
    public void testUpdateOffer(){
        usersDAO.createUser(user1);
        offersDAO.saveOrUpdate(offer1);

        offer1.setText("Offer was updated!");
        offersDAO.saveOrUpdate(offer1);

        Offer retrieved = offersDAO.getOffer(offer1.getId());

        System.out.println("Retrieved: " + retrieved.getText());

        assertEquals(offer1.getText(), retrieved.getText());
    }

    @Test
    public void testDeleteOffer() {
        usersDAO.createUser(user1);
        offersDAO.saveOrUpdate(offer1);
        offersDAO.saveOrUpdate(offer2);

        Offer retrievedOffer = offersDAO.getOffer(offer2.getId());

        assertNotNull(retrievedOffer);

        offersDAO.delete(offer1.getId());

        Offer retrievedNullOffer = offersDAO.getOffer(offer1.getId());

        assertNull(retrievedNullOffer);

        Offer retrievedNotExistingOffer = offersDAO.getOffer(offer4.getId());

        assertNull(retrievedNotExistingOffer);
    }
}
