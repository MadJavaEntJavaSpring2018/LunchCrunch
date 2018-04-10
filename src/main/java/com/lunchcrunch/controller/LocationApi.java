package com.lunchcrunch.controller;

import com.lunchcrunch.entity.Location;
import com.lunchcrunch.entity.User;
import com.lunchcrunch.persistence.GenericDao;
import com.lunchcrunch.persistence.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

/**
 * The LocationApi class contains all the methods needed by the LunchCrunch RestService class to
 * create a location and read all locations on the location table.
 */

public class LocationApi {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final String INVALID_API_KEY = "Invalid Key";
    private static final String NOTHING_FOUND = "";
    private static final String NEW_LOCATION_ADD = "New Location added";

    GenericDao genericDao = new GenericDao(Location.class);
    JsonParser jsonParser = new JsonParser(Location.class);
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
     * @param id            The API key
     * @param userId        The user id
     * @param description   The description
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
            return jsonParser.parseObjectIntoJson(locations);
        } else {
            return NOTHING_FOUND;
        }
    }
}


