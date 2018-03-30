package com.lunchcrunch.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * The UserApi class contains all the methods needed by the LunchCrunch RestService class to
 * create, read, delete & update a user on the user table.
 */
public class UserApi {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new User api.
     */
    public UserApi() {

    }

    /**
     * Add user string.
     *
     * @param lastName  the last name
     * @param firstName the first name
     * @param organisation the organisation
     * @return the string
     */
    public String addUser(String lastName, String firstName, String organisation) {
        String apiKey = "1234567890";

        List<User> users;

        return parseUserIntoJson(users);
    }

    /**
     * Gets all users.
     *
     * @param apiKey the api key
     * @return all users in json format
     */
    public String getAllUsers(String apiKey) {
        List<User> users;

        return parseUserIntoJson(users);
    }

    /**
     * The parseIntoJson method takes the List of User objects and parses them into a json string
     * @param users
     * @return
     */
    private String parseUserIntoJson(List<User> users) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        int jsonObjCount = 0;

        if (users.size() == 0) {
            return "";
        }

        try {
            for (User thisUser : users) {
                jsonObjCount += 1;
                jsonString += mapper.writeValueAsString(thisUser);

                if (jsonObjCount < users.size()) {
                    jsonString += ",";
                }
            }
            if (users.size() > 1) {
                jsonString = "[" + jsonString + "]";
            }
        } catch (JsonGenerationException e) {
            logger.error(e);
        } catch (JsonMappingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }

        return jsonString;

    }


}
