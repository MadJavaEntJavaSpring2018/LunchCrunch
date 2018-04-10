package com.lunchcrunch.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.json.UserJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserJsonTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Verify user json is correct for retrieving a user's data.
     */
    @Test
    void testGetAll() {

        ObjectMapper mapper        = new ObjectMapper();
        JsonFromWebsite myWebsiteJson = new JsonFromWebsite();

        try {

            String jsonInString = myWebsiteJson.readJsonFromUrl("http://18.217.195.143:8080/LunchCrunch/services/" +
                    "lunchcrunch/users?apiKey=0998877543");
            UserJson myUser = mapper.readValue(jsonInString, UserJson.class);
            assertEquals(myUser.getFirstName(),"Hellen");
            assertEquals(myUser.getLastName(),"Brown");

        } catch (Exception e){
            fail("This test has failed due to an exception");
            logger.error(e);
        }

    }

}
