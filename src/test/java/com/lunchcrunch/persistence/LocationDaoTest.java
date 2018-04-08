package com.lunchcrunch.persistence;

import com.lunchcrunch.entity.Location;
import com.lunchcrunch.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lunchcrunch.test.util.Database;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test the Location dao.
 *
 * @author Brian Chase
 */
class LocationDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(Location.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify getting all locations for all users
     */
    @Test
    void getAll() {

        List<Location> locations = (List<Location>)dao.getAll();

        assertNotNull(locations);
        for (Location index : locations) {
            logger.info("location" + index.toString());
            logger.info("test");
        }
        assertEquals(8, locations.size());
    }
    /**
     * Verify that an location can be added for a user and description they chose
     */
    @Test
    void addLocation() {

        GenericDao userDao      = new GenericDao(User.class);
        Location location       = new Location();

        User user               = (User) userDao.getById(2);
        String description      = location.getDescription();

        Location newLocation = new Location(user, description);
        int id = dao.insert(newLocation);

        assertNotEquals(0, id);

    }
}

