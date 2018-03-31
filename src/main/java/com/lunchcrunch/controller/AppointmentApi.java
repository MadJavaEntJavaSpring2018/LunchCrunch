package edu.matc.patientWebService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Appointment;
import com.lunchcrunch.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/appointments")
public class AppointmentApi {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "json"
    @Produces("application/json")

    public Response getLetter() throws JsonProcessingException {
        // Return a simple message

        ObjectMapper mapper = new ObjectMapper();
        String jformatoutput = "[";

        GenericDao genericDao = new GenericDao(Appointment.class);
        Appointment patient = (Appointment) genericDao.getById(1);
        //String output = patient.getNotes();

        jformatoutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(patient.getFirstName()) + "]";

        //return Response.status(200).entity(output).build();
        return Response.ok(jformatoutput, MediaType.APPLICATION_JSON).build();

    }
}