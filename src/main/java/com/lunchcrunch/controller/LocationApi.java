package com.lunchcrunch.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Location;
import com.lunchcrunch.entity.User;
import com.lunchcrunch.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.List;

public class LocationApi {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final String INVALID_API_KEY = "Invalid Key";
    private static final String NOTHING_FOUND = "";
    private static final String NEW_LOCATION_ADD = "New Location added";

    GenericDao genericDao = new GenericDao(Location.class);
    /**
     * Instantiates a new Location api.
     */
    public LocationApi() {
    }
    /**
     * Add a location.
     *
     * If the appointment Id was sent, then update otherwise insert
     *
     * @param id          The API key
     * @param userId  The user id
     * @param description The description
     *
     * @return all locations in json format
     */
    public String addLocation(int id, int userId, String description) {

        int newLocationId = 0;
        String message = " ";
            newLocationId = createLocation(userId, description);
            message = (NEW_LOCATION_ADD + " " + "id = " + newLocationId);
        return message;
    }
    /**
     * Add location
     *
     * @param userId      the user id
     * @param description the description
     * @return the string
     */
    public int createLocation(int userId, String description) {

        GenericDao userDao      = new GenericDao(User.class);

        User user               = (User) userDao.getById(userId);

        Location newLocation = new Location(user, description);
        return genericDao.insert(newLocation);
    }
    /**
     * Get all locations for the organization.
     *
     * @param
     * @return all locations in json format
     */
    public String getAllLocations(String apiKey) {

        UserApi userApi = new UserApi();
        int id  =  userApi.getUserId(apiKey);
        if (id == -1) {
            return INVALID_API_KEY;
        }

        List<Location> locations = (List<Location>) genericDao.getAll();

        if (locations.size() > 0) {
            return parseObjectIntoJson(locations);
        } else {
            return NOTHING_FOUND;
        }
    }
    /**
     * The parseIntoJson method takes the List of Location objects and parses them into a json string
     *
     * @param locations
     * @return
     */
    private String parseObjectIntoJson(List<Location> locations) {
        ObjectMapper mapper = new ObjectMapper();
        String jasonOutput = "[";
        int count = 0;

        if (locations.size() == 0) {
            return "";
        }
        try {
            for (Location index : locations)  {
                count = count + 1;
                if (count == locations.size()) {
                    jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + "]";
                } else {
                    jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + ",";
                }
            }
        } catch (JsonGenerationException e) {
            logger.error(e);
        } catch (JsonMappingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return jasonOutput;
    }
}


