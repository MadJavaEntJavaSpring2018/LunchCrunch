package com.lunchcrunch.persistence;

import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TopicDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(Topic.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify getting all topics for all users
     */
    @Test
    void testGetAll() {

        List<Topic> topics = (List<Topic>)dao.getAll();

        assertNotNull(topics);
        for (Topic index : topics) {
            logger.info("topic" + index.toString());
            logger.info("test");
        }
        assertEquals(4, topics.size());
    }

    /**
     * Verify the success of getting by id
     */
    @Test
    void testGetById() {

        Topic retrievedTopic = (Topic) dao.getById(4);
        assertNotNull(retrievedTopic);
        assertEquals("Pets", retrievedTopic.getDescription());

    }

    /**
     * Verify that an topic can be added for a user and description they chose
     */
    @Test
    void addTopic() {

        Topic retrieveNewTopic = new Topic();

        Topic newTopic = new Topic("Sports");
        int id = dao.insert(newTopic);

        retrieveNewTopic = (Topic) dao.getById(id);

        assertEquals(newTopic, retrieveNewTopic);

    }

}
