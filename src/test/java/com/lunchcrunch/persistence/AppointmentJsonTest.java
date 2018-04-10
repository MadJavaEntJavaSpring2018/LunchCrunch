package com.lunchcrunch.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.json.AppointmentJson;
import com.lunchcrunch.json.LocationJson;
import com.lunchcrunch.json.UserJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test Json retrieved from AWS LunchCrunch website for Appointment Api.
 */
public class AppointmentJsonTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Verify json is retrieved by user
     */
    @Test
    void testGetByUserKey() {

        ObjectMapper    mapper        = new ObjectMapper();
        JsonFromWebsite myWebsiteJson = new JsonFromWebsite();

        try {
            String jsonInString = myWebsiteJson.readJsonFromUrl("http://18.217.195.143:8080/LunchCrunch/services/" +
                    "lunchcrunch/appointments?apiKey=1234567890");
            List<AppointmentJson> myAppointments = mapper.readValue(jsonInString,
                    new TypeReference<List<AppointmentJson>>(){});
            assertEquals(4, myAppointments.size());
        } catch (Exception e){
            fail("This test has failed due to an exception");
            logger.error(e);
        }

    }

    /**
     * Verify json is retrieved by Location
     */
    @Test
    void testGetByLocation() {

        ObjectMapper    mapper        = new ObjectMapper();
        JsonFromWebsite myWebsiteJson = new JsonFromWebsite();

        try {
            String jsonInString = myWebsiteJson.readJsonFromUrl("http://18.217.195.143:8080/LunchCrunch/services/" +
                    "lunchcrunch/appointments/location?location=4");
            List<AppointmentJson> myAppointments = mapper.readValue(jsonInString,
                    new TypeReference<List<AppointmentJson>>(){});
            assertEquals(2, myAppointments.size());
        } catch (Exception e){
            fail("This test has failed due to an exception");
            logger.error(e);
        }

    }

    /**
     * Verify json is retrieved by Location
     */
    @Test
    void testGetByTopic() {

        ObjectMapper    mapper        = new ObjectMapper();
        JsonFromWebsite myWebsiteJson = new JsonFromWebsite();

        try {
            String jsonInString = myWebsiteJson.readJsonFromUrl("http://18.217.195.143:8080/LunchCrunch/services/" +
                    "lunchcrunch/appointments/topic?topic=2");
            List<AppointmentJson> myAppointments = mapper.readValue(jsonInString,
                    new TypeReference<List<AppointmentJson>>(){});
            assertEquals(3, myAppointments.size());
        } catch (Exception e){
            fail("This test has failed due to an exception");
            logger.error(e);
        }

    }

    /**
     * Verify json is retrieved by Appointment Id.
     */
    @Test
    void testGetByAppointmentId() {

        ObjectMapper    mapper        = new ObjectMapper();
        JsonFromWebsite myWebsiteJson = new JsonFromWebsite();

        try {
            String jsonInString = myWebsiteJson.readJsonFromUrl("http://18.217.195.143:8080/LunchCrunch/services/" +
                    "lunchcrunch/appointments/appointment?appointment=2");
            AppointmentJson myAppointment = mapper.readValue(jsonInString, AppointmentJson.class);
            assertEquals(myAppointment.getId(),2);
        } catch (Exception e){
            fail("This test has failed due to an exception");
            logger.error(e);
        }

    }

}
