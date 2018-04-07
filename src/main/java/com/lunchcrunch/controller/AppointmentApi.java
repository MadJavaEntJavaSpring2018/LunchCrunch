package com.lunchcrunch.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Appointment;
import com.lunchcrunch.entity.Location;
import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.entity.User;
import com.lunchcrunch.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * The AppointmentApi class contains all the methods needed by the LunchCrunch RestService class to
 * create, read, delete & update a appointment on the appointment table.
 */
public class AppointmentApi {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final String INVALID_API_KEY = "Invalid Key";
    private static final String NOTHING_FOUND = "";

    GenericDao dao = new GenericDao(Appointment.class);

    /**
     * Instantiates a new Appointment api.
     */
    public AppointmentApi() {
    }

    /**
     * Add appointment
     *
     * @param userId     the user id
     * @param locationId the location id
     * @param topicId    the topic id
     * @param dateTime   the date and time of appointment
     * @return the string
     */
    public String addAppointment(int userId, int locationId, int topicId, LocalDateTime dateTime) {

        GenericDao userDao      = new GenericDao(User.class);
        GenericDao locationDao  = new GenericDao(Location.class);
        GenericDao topicDao     = new GenericDao(Topic.class);

        User     user       = (User) userDao.getById(userId);
        Location location   = (Location) locationDao.getById(locationId);
        Topic    topic      = (Topic) topicDao.getById(topicId);

        Appointment newAppointment = new Appointment(user, location, topic, LocalDateTime.now());

        int id = dao.insert(newAppointment);

        return "ok";
    }


//    /**
//     * This processUser method can add, update and delete a user.
//     *
//     * @param apiKey
//     * @param firstName
//     * @param lastName
//     * @param organization
//     * @return Response
//     */
//    public Response processAppointment(String apiKey) {
//
//        if (apiKey == null || apiKey.isEmpty()) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(BAD_REQUEST_MSG).build();
//        }
//
//        String jsonString = getSpecificUser(apiKey);
//
//        if (jsonString.isEmpty() || jsonString.equals(INVALID_KEY_MSG)) {
//            ret
// urn Response.status(Response.Status.NOT_FOUND).entity(NOTHING_FOUND_MSG).build();
//        } else {
//            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
//        }
//    }

    /**
     * Get all appointments for the organization.
     *
     * @param
     * @return all appointments in json format
     */
    public String getAllAppointments(int userid) {

        List<Appointment> appointments = (List<Appointment>) dao.getByColumnInt("user", userid);

        if (appointments.size() > 0) {
            return parseObjectIntoJson(appointments);
        } else {
            return NOTHING_FOUND;
        }
    }

    /**
     * Get all appointments for the organization.
     *
     * @param
     * @return all appointments in json format
     */
    public String getAllAppointmentsByLocation(int location) {

        List<Appointment> appointments = (List<Appointment>) dao.getByColumnInt("location", location);

        if (appointments.size() > 0) {
            return parseObjectIntoJson(appointments);
        } else {
            return NOTHING_FOUND;
        }
    }

    /**
     * Get all appointments for the organization.
     *
     * @param
     * @return all appointments in json format
     */
    public String getAllAppointmentsByTopic(int topic) {

        List<Appointment> appointments = (List<Appointment>) dao.getByColumnInt("topic", topic);

        if (appointments.size() > 0) {
            return parseObjectIntoJson(appointments);
        } else {
            return NOTHING_FOUND;
        }
    }

    /**
     * The parseIntoJson method takes the List of Appointment objects and parses them into a json string
     *
     * @param appointments
     * @return
     */
    private String parseObjectIntoJson(List<Appointment> appointments) {
        ObjectMapper mapper = new ObjectMapper();
        String jasonOutput = "[";
        int count = 0;

        if (appointments.size() == 0) {
            return "";
        }
        try {
            for (Appointment index : appointments)  {
                count = count + 1;
                if (count == appointments.size()) {
                    jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + "]";
                } else {
                    jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + ",";
                }
            }
        } catch (JsonGenerationException e) {
            logger.error(e);
        } catch (JsonMappingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return jasonOutput;
    }
}