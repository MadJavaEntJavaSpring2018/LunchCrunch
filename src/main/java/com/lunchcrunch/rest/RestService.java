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
 */
@Path("/lunchcrunch")
public class RestService {


    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets a user and returns it as json.
     *
     * @param apiKey the api key
     * @return the all users
     */
    @GET
    @Path("/users")
    public Response getUser(@QueryParam("apiKey") String apiKey) {
        UserApi userApi = new UserApi();
        return userApi.processUser(apiKey);
    }


    /**
     * Create, update or delete the user
     *
     * @param apiKey       the api key
     * @param firstName    the first name
     * @param lastName     the last name
     * @param organization the organization
     * @return the response
     */
    @POST
    @Path("/users")
    public Response addUpdateDeleteUser(@FormParam("apiKey") String apiKey,
                                        @FormParam("firstname") String firstName,
                                        @FormParam("lastname") String lastName,
                                        @FormParam("organization") String organization) {
        UserApi userApi = new UserApi();
        return userApi.processUser(apiKey, firstName, lastName, organization);
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

    /**
     * Create appointment response.
     *
     * @param userId    the user id
     * @param firstName the first name
     * @return the response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/appointments/get/{userId}")
    public Response createAppointment(@PathParam("userId") int userId,
                                      @QueryParam("name") String firstName) {

        logger.info("I passed the user id with @PathParam   : " + userId);
        logger.info("I passed the fist name with @QueryParam: " + firstName);
        //logger.info("WHAT THE HECk: {} {} {} {} " + userId + locationId );
        AppointmentApi appointApi = new AppointmentApi();

        //String response = appointApi.addAppointment(userId,locationId,topicId,LocalDateTime.parse(date));

        return Response.ok("ok", MediaType.TEXT_PLAIN).build();
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