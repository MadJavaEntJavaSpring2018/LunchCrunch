package com.lunchcrunch.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.json.TopicJson;
import com.lunchcrunch.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for Topic json.
 */
public class TopicJsonTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Verify topic json is correct
     */
    @Test
    void testGetAll() {

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "[{\"id\" : 1,\"description\" : \"Healthy Eating\"},{\"id\" : 2,\"description\" : \"Movie of the day\"},{\"id\" : 3,\"description\" : \"Good places to eat\"},{\"id\" : 4,\"description\" : \"Pets\"}]";

        try {
            List<TopicJson> myTopics = mapper.readValue(jsonInString, new TypeReference<List<TopicJson>>(){});
            assertEquals(4, myTopics.size());
        } catch (Exception e){
            fail("This test has failed due to an exception");
            //logger.error(e);
        }

    }

}
