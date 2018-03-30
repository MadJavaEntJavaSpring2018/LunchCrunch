package com.lunchcrunch.rest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.controller.UserApi;
import com.lunchcrunch.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * The RestService class contains one method each for each of the API available functions
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
    @Path("/users")
    public Response getAllUsers() {
        userApi = new UserApi();

        //TODO remove test variables
        String apiKey = "1234567890";

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
    @Path("/users")
    public Response createUser() {
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

    }

    /**
     * Create appointment response.
     *
     * @return the response
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/appointments")
    public Response createAppointment() {

    }

    /**
     * Gets all locations.
     *
     * @return the all locations
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/locations")
    public Response getAllLocations() {

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

    }

}
