package com.lunchcrunch.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.User;
import com.lunchcrunch.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * The UserApi class contains all the methods needed by the LunchCrunch RestService class to
 * create, read, delete & update a user on the user table.
 */
public class UserApi {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private static final String INVALID_API_KEY = "Invalid Key";
    private static final String NOTHING_FOUND = "";


    /**
     * The User dao.
     */
    GenericDao userDao = new GenericDao(User.class);

    /**
     * Instantiates a new User api.
     */
    public UserApi() {

    }

    /**
     * Add user
     *
     * @param lastName     the last name
     * @param firstName    the first name
     * @param organisation the organisation
     * @return the string
     */
    public String addUser(String lastName, String firstName, String organisation) {

        String apiKey = generateApiKey();

        User user = new User(apiKey, TRUE, LocalDateTime.now(), firstName, lastName, organisation);

        int id = userDao.insert(user);

        return apiKey;
    }

    /**
     * Get all users.
     *
     * @param apiKey the api key
     * @return all users in json format
     */
    public String getAllUsers(String apiKey) {

        if (!validApiKey(apiKey)) {
            return INVALID_API_KEY;
        }

        List<User> users = userDao.getAll();

        if (users.size() > 0) {
            return parseUserIntoJson(users);
        } else {
            return NOTHING_FOUND;
        }
    }


    /**
     * The validateApiKey method will validate that the API key passed to it exists on the user table
     *
     * @param apiKey the api key
     * @return the boolean
     */
    public boolean validApiKey(String apiKey) {

        List<User> users = userDao.getByPropertyEqual("key", apiKey);

        if (users.size() == 0) {
            return FALSE;
        } else {
            return TRUE;
        }
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

    /**
     * The generateApiKey method generates the API Key
     *
     * @return String API Key
     */
    private String generateApiKey() {

        String apiKey = "";
        while (TRUE) {
            apiKey = generateRandomString();
            if (!validApiKey(apiKey)) {
                break;
            }
        }

        return apiKey;
    }

    /**
     * Generate API key string
     *
     * @return 10 byte string containing random characters
     */
    private String generateRandomString() {

        String stringSeed = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder();
        Random rnd = new Random();

        while (randomString.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * stringSeed.length());
            randomString.append(stringSeed.charAt(index));
        }
        String randomStringStr = randomString.toString();
        return randomStringStr;
    }
}
