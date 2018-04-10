package com.lunchcrunch.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.json.LocationJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test Json retrieved from AWS LunchCrunch website for Location Api.
 */
public class LocationJsonTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Verify location json is correct for the Get function
     */
    @Test
    void testGetAll() {

        ObjectMapper    mapper        = new ObjectMapper();
        JsonFromWebsite myWebsiteJson = new JsonFromWebsite();

        try {
            String jsonInString = myWebsiteJson.readJsonFromUrl("http://18.217.195.143:8080/LunchCrunch/services/" +
                    "lunchcrunch/locations?apiKey=0998877543");
            List<LocationJson> myLocations = mapper.readValue(jsonInString, new TypeReference<List<LocationJson>>(){});
            assertEquals(8, myLocations.size());
        } catch (Exception e){
            fail("This test has failed due to an exception");
            logger.error(e);
        }

    }

}
