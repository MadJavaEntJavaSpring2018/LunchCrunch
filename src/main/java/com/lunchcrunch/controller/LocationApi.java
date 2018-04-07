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
    public String createLocation(int userId, String description) {

        GenericDao userDao      = new GenericDao(User.class);
        Location location       = new Location();

        User user               = (User) userDao.getById(userId);
        description             = location.getDescription();

        Location newLocation = new Location(user, description);
        genericDao.insert(newLocation);

        return "Location added";
    }
    /**
     * Delete location
     *
     * @param userId      the user id
     * @param description the description
     * @return the string
     */
    public String deleteLocation(int userId, String description) {

        GenericDao userDao      = new GenericDao(User.class);
        Location location       = new Location();

        User user               = (User) userDao.getById(userId);
        description             = location.getDescription();

        Location deleteLocation = new Location(user, description);
        genericDao.delete(deleteLocation);

        return "Location deleted";
    }
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


