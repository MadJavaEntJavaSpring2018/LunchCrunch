package com.lunchcrunch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Appointment;
import com.lunchcrunch.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/appointments")
public class AppointmentApi {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "json"
    @Produces("application/json")

    public Response getAllAppointments() throws Exception {

        GenericDao genericDao = new GenericDao(Appointment.class);
        List<Appointment> appointments = (List<Appointment>)genericDao.getAll();

        ObjectMapper mapper         = new ObjectMapper();
        String       jasonOutput    = "[";
        int          count          = 0;

        for (Appointment index : appointments)  {
            count = count + 1;
            if (count == appointments.size()) {
                jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + "]";
            } else {
                jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + ",";
            }
        }

        //return Response.status(200).entity(output).build();
        return Response.ok(jasonOutput, MediaType.APPLICATION_JSON).build();
    }
}

