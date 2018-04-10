package com.lunchcrunch.rest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.controller.AppointmentApi;
import com.lunchcrunch.controller.LocationApi;
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


    /** Gets all appointments for a specific user and returns it as json.
     *
     * @param apiKey the api key
     * @return the all appointments
     */
    @GET
    @Path("/appointments")
    public Response getAllAppointmentsForUser(@QueryParam("apiKey") String apiKey) {

        UserApi userApi = new UserApi();
        int id = userApi.getUserId(apiKey);

        AppointmentApi appointApi = new AppointmentApi();
        String jsonString           = appointApi.getAllAppointments(id);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }


    /**
     * Gets all appointments for a specific location.
     *
     * @param location the Location id
     * @return the appointments
     */
    @GET
    @Path("/appointments/location")
    public Response getAllAppointmentsByLocation(@QueryParam("location") int location) {

        AppointmentApi appointApi = new AppointmentApi();

        String jsonString           = appointApi.getAllAppointmentsByLocation(location);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }


    /**
     * Gets all appointments for a specific topic and returns it as json.
     *
     * @param topic the Topic Id
     * @return the all appointments
     */
    @GET
    @Path("/appointments/topic")
    public Response getAllAppointmentsByTopic(@QueryParam("topic") int topic) {

        AppointmentApi appointApi = new AppointmentApi();
        String jsonString           = appointApi.getAllAppointmentsByTopic(topic);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * Gets all appointments for a specific topic and returns it as json.
     *
     * @param appointment the Appointment Id
     * @return the appointments
     */
    @GET
    @Path("/appointments/appointment")
    public Response getAllAppointmentsByAppointment(@QueryParam("appointment") int appointment) {

        AppointmentApi appointApi = new AppointmentApi();
        String jsonString           = appointApi.getAllAppointmentsByAppointment(appointment);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }


    /**
     * Add a new appointment
     *
     * @param apikey       the api key
     * @param location     the Location Id
     * @param topic        the Topic Id
     * @param datetime     the meeting date/time
     * @return the response
     */
    @POST
    @Path("/appointments")
    public Response addUpdateAppointment(@FormParam("apikey")        String apikey,
                                         @FormParam("appointmentid") int    appointment,
                                         @FormParam("locationid")    int    location ,
                                         @FormParam("topicid")       int    topic,
                                         @FormParam("datetime")      String datetime) {

        UserApi userApi = new UserApi();
        int id = userApi.getUserId(apikey);

        AppointmentApi appointmentApi = new AppointmentApi();

        String jsonString  =
                appointmentApi.maintainAppointment(id, appointment, location, topic, datetime);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }


    /**
     * Gets all locations.
     *
     * @return the all locations
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("/locations")
    public Response getAllLocations(@QueryParam("apiKey") String apiKey) {

        LocationApi locationApi = new LocationApi();

        String jsonString        = locationApi.getAllLocations(apiKey);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * Add a new location
     *
     * @param apikey       the api key
     * @param userId       the User Id
     * @param description  the Description
     * @return the response
     */
    @POST
    @Path("/locations")
    public Response addLocation(@FormParam("apikey")        String apikey,
                                @FormParam("userId")        int userId,
                                @FormParam("description")   String description) {

        UserApi userApi = new UserApi();
        int id = userApi.getUserId(apikey);

        LocationApi locationApi = new LocationApi();
        String jsonString        = locationApi.addLocation(id, userId, description);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * Gets all topics.
     *
     * @return the all topics
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/topics")
    public Response getAllTopics(@QueryParam("apiKey") String apiKey) {

        TopicApi currentTopicApi = new TopicApi();

        String jsonString        = currentTopicApi.getAllTopics(apiKey);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }

    }
}