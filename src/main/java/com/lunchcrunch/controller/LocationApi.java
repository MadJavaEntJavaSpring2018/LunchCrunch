package com.lunchcrunch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Location;
import com.lunchcrunch.entity.User;
import com.lunchcrunch.persistence.GenericDao;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class LocationApi {
    GenericDao genericDao = new GenericDao(Location.class);
    /**
     * Instantiates a new Location api.
     */
    public LocationApi() {
    }
    /**
     * Add location
     *
     * @param userId      the user id
     * @param description the description
     * @return the string
     */
    public String addLocation(int userId, String description) {

        GenericDao userDao      = new GenericDao(User.class);
        Location location       = new Location();

        User user               = (User) userDao.getById(userId);
        description             = location.getDescription();

        Location newLocation = new Location(user, description);
        int id = genericDao.insert(newLocation);

        return "ok";
    }

    public Response getAllLocations() throws Exception {

        List<Location> locations = (List<Location>)genericDao.getAll();

        ObjectMapper mapper         = new ObjectMapper();
        String       jasonOutput    = "[";
        int          count          = 0;

        for (Location index : locations)  {
            count = count + 1;
            if (count == locations.size()) {
                jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + "]";
            } else {
                jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + ",";
            }
        }
        validApiKey();
        //return Response.status(200).entity(output).build();
        return Response.ok(jasonOutput, MediaType.APPLICATION_JSON).build();
    }
    public Response validApiKey() {
        String apiKey = "";
        UserApi userApi = new UserApi();
        Boolean jasonOutput = Boolean.FALSE;
        userApi.validApiKey(apiKey);
        //return Response.status(200).entity(output).build();
        return Response.ok(jasonOutput, MediaType.APPLICATION_JSON).build();
    }
}


