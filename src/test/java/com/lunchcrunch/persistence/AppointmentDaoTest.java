package com.lunchcrunch.persistence;

import com.lunchcrunch.entity.Appointment;
import com.lunchcrunch.entity.Location;
import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lunchcrunch.test.util.Database;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test the pack dao.
 *
 * @author elise strauss
 */
class AppointmentDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(Appointment.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify getting all appointment for all users
     */
    @Test
    void getAll() {

        List<Appointment> appointments = (List<Appointment>)dao.getAll();

        assertNotNull(appointments);
        for (Appointment index : appointments) {
            logger.info("appointment" + index.toString());
            logger.info("test");
        }
        assertEquals(9, appointments.size());

    }

    /**
     * Verify getting all appointment for a specific users
     */
    @Test
    void getAllAppointmentsForUser() {

        List<Appointment> appointments =
                (List<Appointment>)dao.getByColumnInt("user", 1);

        assertNotNull(appointments);
        for (Appointment index : appointments) {
            logger.info("appointment" + index.toString());
        }
        assertEquals(4, appointments.size());
    }

    /**
     * Verify getting all appointment for a specific location
     */
    @Test
    void getAllAppointmentsForLocation() {

        List<Appointment> appointments =
                (List<Appointment>)dao.getByColumnInt("location", 1);

        assertNotNull(appointments);
        for (Appointment index : appointments) {
            logger.info("appointment" + index.toString());
        }
        assertEquals(2, appointments.size());
    }

    /**
     * Verify getting all appointment for a specific topic
     */
    @Test
    void getAllAppointmentsForTopic() {

        List<Appointment> appointments =
                (List<Appointment>)dao.getByColumnInt("topic", 1);

        assertNotNull(appointments);
        for (Appointment index : appointments) {
            logger.info("appointment" + index.toString());
        }
        assertEquals(3, appointments.size());
    }


    /**
     * Verify that an appointment can be added for a user, topic and location they chose
     */
    @Test
    void addAppointment() {

        GenericDao userDao      = new GenericDao(User.class);
        GenericDao locationDao  = new GenericDao(Location.class);
        GenericDao topicDao     = new GenericDao(Topic.class);

        User     user       = (User) userDao.getById(3);
        Location location   = (Location) locationDao.getById(7);
        Topic    topic      = (Topic) topicDao.getById(2);

        Appointment newAppointment = new Appointment(user, location, topic, LocalDateTime.now());

        int id = dao.insert(newAppointment);

        assertNotEquals(0, id);

    }
}
