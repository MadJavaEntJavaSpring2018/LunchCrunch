package com.lunchcrunch.rest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.controller.AppointmentApi;
import com.lunchcrunch.controller.TopicApi;
import com.lunchcrunch.controller.UserApi;
import com.lunchcrunch.entity.Appointment;
import com.lunchcrunch.entity.Location;
import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.entity.User;
import com.lunchcrunch.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The RestService class contains one method each for each of the API available functions:
 *
 *
 */
@Path("/lunchcrunch")
public class RestService {

    UserApi userApi;


    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/{apiKey}")
    public Response getAllUsers(@PathParam("apiKey") String apiKey) {
        userApi = new UserApi();

        String jsonString  = userApi.getAllUsers(apiKey);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * Create user response.
     *
     * @return the response
     */
    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/{param}")
    public Response createUser(@PathParam("param") String param) {
        userApi = new UserApi();

        //TODO remove test variables
        String lastName = "Smith";
        String firstName = "Bonny";
        String organization = "Nelnet";

        String apiKey = userApi.addUser(lastName, firstName, organization);

        if (apiKey.isEmpty()) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Service Unavailable").build();
        } else {
            return Response.ok(userApi, MediaType.TEXT_PLAIN).build();
        }
    }

    /**
     * Gets all appointments.
     *
     * @return the all appointments
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/appointments")
    public Response getAllAppointments() {

        AppointmentApi appointApi   = new AppointmentApi();

        String jsonString           = appointApi.getAllAppointments();

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }

//    /**
//     * Create appointment response.
//     *
//     * @return the response
//     */
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/appointments/{userId}{locationId}{topicId}{date}")
//    public Response createAppointment(@PathParam("userId") int userId,
//                                      @PathParam("locationId") int locationId,
//                                      @PathParam("topicId") int topicId,
//                                      @PathParam("date") String date) {
//
//        logger.info("WHAT THE HECk: {} {} {} {} " + userId + locationId );
//        AppointmentApi appointApi = new AppointmentApi();
//
//        String response = appointApi.addAppointment(userId,locationId,topicId,LocalDateTime.parse(date));
//
//        return Response.ok(response, MediaType.TEXT_PLAIN).build();
//    }

    /**
     * Gets all locations.
     *
     * @return the all locations
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/locations")
    public Response getAllLocations() {

        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Service Unavailable").build();
    }

    /**
     * Createlocation response.
     *
     * @return the response
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/locations")
    public Response createlocation() {

        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Service Unavailable").build();
    }

    /**
     * Gets all topics.
     *
     * @return the all topics
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/topics")
    public Response getAllTopics() {

        TopicApi currentTopicApi = new TopicApi();

        String jsonString        = currentTopicApi.getAllTopics();

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }

    }

    /**
     * Create topic response.
     *
     * @return the response
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/topics")
    public Response createTopic() {

        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Service Unavailable").build();
    }

}
