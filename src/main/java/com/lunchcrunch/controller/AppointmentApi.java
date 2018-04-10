package com.lunchcrunch.controller;

import com.lunchcrunch.entity.Appointment;
import com.lunchcrunch.entity.Location;
import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.entity.User;
import com.lunchcrunch.persistence.GenericDao;
import com.lunchcrunch.persistence.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    GenericDao dao        = new GenericDao(Appointment.class);
    JsonParser jsonParser = new JsonParser(User.class);

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
            return jsonParser.parseObjectIntoJson(appointments);
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
            return jsonParser.parseObjectIntoJson(appointments);
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
            return jsonParser.parseObjectIntoJson(appointments);
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
            return jsonParser.parseObjectIntoJson(appointments);
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
        GenericDao appointDao = new GenericDao(Appointment.class);

        int newAppointmentId = 0;
        int appointmentId = 0;
        String message = " ";

        if (appointment == 0) {
            logger.info("This will be an add" + id + location + topic + datetime);
            newAppointmentId = addAppointment(id, location, topic, datetime);
            message = (NEW_APPOINTMENT_ADD + " " + "id = " + newAppointmentId);
            appointmentId = newAppointmentId;

        } else {
            logger.info("This will be an update" + appointment + datetime);
            updateAppointment(appointment, datetime);
            message = (APPOINTMENT_UPDATED + " " + "id = " + appointment);
            appointmentId = appointment;
        }

        logger.info("Getting appointment by id:" + appointmentId);

        Appointment appoint = (Appointment)appointDao.getById(appointmentId);

        List<Appointment> appoints = new ArrayList<>();
        appoints.add(appoint);

        JsonParser jsonParser = new JsonParser(Appointment.class);
        message = jsonParser.parseObjectIntoJson(appoints);

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


}