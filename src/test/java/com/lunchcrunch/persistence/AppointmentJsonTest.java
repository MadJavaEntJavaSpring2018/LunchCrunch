package com.lunchcrunch.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.json.AppointmentJson;
import com.lunchcrunch.json.LocationJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppointmentJsonTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Verify location json is correct
     */
    @Test
    void testGetAll() {

        ObjectMapper    mapper        = new ObjectMapper();
        JsonFromWebsite myWebsiteJson = new JsonFromWebsite();

        try {
            String jsonInString = myWebsiteJson.readJsonFromUrl("http://18.217.195.143:8080/LunchCrunch/services/" +
                    "lunchcrunch/appointments?apiKey=1234567890");
            List<AppointmentJson> myLocations = mapper.readValue(jsonInString,
                    new TypeReference<List<AppointmentJson>>(){});
            assertEquals(4, myLocations.size());
        } catch (Exception e){
            fail("This test has failed due to an exception");
            logger.error(e);
        }

    }

}
