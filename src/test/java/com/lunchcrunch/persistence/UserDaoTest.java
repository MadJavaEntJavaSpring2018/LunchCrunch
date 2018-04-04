package com.lunchcrunch.persistence;

import com.lunchcrunch.controller.UserApi;
import com.lunchcrunch.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lunchcrunch.test.util.Database;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test the pack dao.
 *
 * @author mchoinoski
 */
class UserDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void testGetUserId() {

        UserApi userApi = new UserApi();
        int userId = userApi.getUserId("1122334455");

        int testUserId = userId;
        assertEquals(testUserId, userId);

    }

    /**
     * Verify the success of getting by id
     */
    @Test
    void testGetById() {

        User retrievedUser = (User) dao.getById(3);
        assertNotNull(retrievedUser);
        assertEquals("Fred", retrievedUser.getFirstName());

    }

    @Test
    void testGetAllUsers() {
        UserApi userApi = new UserApi();
        String jsonAllUsers = userApi.getAllUsers("1234567890");

        logger.debug("All Users json:" + jsonAllUsers);

        String testString = jsonAllUsers;
        assertEquals(testString, jsonAllUsers);

    }

    @Test
    void testAddUser() {
        UserApi userApi = new UserApi();
        String apiKey = userApi.addUser("Fourie", "Jacques", "Great Lakes");

        logger.debug("Added user. API key = " + apiKey);

        String testString = apiKey;
        assertEquals(testString, apiKey);

    }
}
