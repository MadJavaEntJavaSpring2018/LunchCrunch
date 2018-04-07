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
import java.util.ArrayList;
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
    private static final String NEW_APPOINTMENT_ADD = "New Appointment added";
    private static final String APPOINTMENT_UPDATED = "Appointment updated";

    GenericDao dao = new GenericDao(Appointment.class);

    /**
     * Instantiates a new Appointment api.
     */
    public AppointmentApi() {
    }


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
     * Get all appointments for the organization.
     *
     * @param
     * @return all appointments in json format
     */
    public String getAllAppointmentsByAppointment(int appointment) {

        List<Appointment> appointments = new ArrayList<Appointment>();

        Appointment appointment1 = (Appointment) dao.getById(appointment);
        appointments.add(appointment1);

        if (appointments.size() > 0) {
            return parseObjectIntoJson(appointments);
        } else {
            return NOTHING_FOUND;
        }
    }

    /**
     * Add or Update an appointment.
     *
     * If the appointment Id was sent, then update otherwise insert
     *
     * @param id          The user id
     * @param appointment The appointment id
     * @param location    The location id
     * @param topic       The topic id
     * @param datetime    The meeting date and time
     *
     * @return all appointments in json format
     */
    public String maintainAppointment(int id, int appointment, int location, int topic, String datetime) {

        int newAppointmentId = 0;
        String message = " ";

        if (appointment == 0) {
            logger.info("This will be an add" + id + location + topic + datetime);
            newAppointmentId = addAppointment(id, location, topic, datetime);
            message = (NEW_APPOINTMENT_ADD + " " + "id = " + newAppointmentId);

        } else {
            logger.info("This will be an update" + appointment + datetime);
            updateAppointment(appointment, datetime);
            message = (APPOINTMENT_UPDATED + " " + "id = " + appointment);
        }
        return message;
    }


    /**
     * Add appointment
     *
     * @param userId     the user id
     * @param locationId the location id
     * @param topicId    the topic id
     * @param datetime   the date and time of appointment
     * @return the string
     */
    public int addAppointment(int userId, int locationId, int topicId, String datetime) {

        GenericDao userDao      = new GenericDao(User.class);
        GenericDao locationDao  = new GenericDao(Location.class);
        GenericDao topicDao     = new GenericDao(Topic.class);

        User     user       = (User) userDao.getById(userId);
        Location location   = (Location) locationDao.getById(locationId);
        Topic    topic      = (Topic) topicDao.getById(topicId);

        Appointment newAppointment = new Appointment(user, location, topic, LocalDateTime.parse(datetime));

        return dao.insert(newAppointment);
    }

    /**
     * Update appointment date and time
     *
     * @param id        The appointment id
     * @param datetime  The new date and time to meet
     * @return the string
     */
    public void updateAppointment(int id, String datetime) {

        Appointment updatedAppointment = (Appointment)dao.getById(id);

        updatedAppointment.setDateTime(LocalDateTime.parse(datetime));

        dao.saveOrUpdate(updatedAppointment);


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