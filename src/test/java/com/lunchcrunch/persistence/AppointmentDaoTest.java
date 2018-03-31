package com.lunchcrunch.persistence;

import com.lunchcrunch.entity.Appointment;
import com.lunchcrunch.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lunchcrunch.test.util.Database;

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
}
