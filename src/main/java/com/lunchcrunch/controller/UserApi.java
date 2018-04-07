package com.lunchcrunch.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.User;
import com.lunchcrunch.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
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
    private static final String INVALID_KEY_MSG = "Invalid Key";
    private static final String NOTHING_FOUND_MSG = "Nothing Found";
    private static final String NOTHING_FOUND = "";
    private static final String BAD_REQUEST_MSG = "Bad Request";
    private static final String SERVICE_UNAVAILABLE_MSG = "Service Unavailable";

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
     * This processUser method returns a specific user in json format.
     *
     * @param apiKey
     * @return
     */
    public Response processUser(String apiKey) {

        if (apiKey == null || apiKey.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(BAD_REQUEST_MSG).build();
        }

        String jsonString = getSpecificUser(apiKey);

        if (jsonString.isEmpty() || jsonString.equals(INVALID_KEY_MSG)) {
            return Response.status(Response.Status.NOT_FOUND).entity(NOTHING_FOUND_MSG).build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }


    /**
     * This processUser method can add, update and delete a user.
     *
     * @param apiKey
     * @param firstName
     * @param lastName
     * @param organization
     * @return Response
     */
    public Response processUser(String apiKey, String firstName, String lastName, String organization) {

        // No apiKey passed, so try to add the user
        if ((apiKey==null || apiKey.isEmpty())) {
            return newUser(firstName, lastName, organization);
        }

        // An API key was passed and nothing else, delete the user
        if ((firstName == null || firstName.isEmpty()) &&
                (lastName == null || lastName.isEmpty()) &&
                (organization == null || organization.isEmpty())) {
            return deleteUser(apiKey);
        }

        // If the two conditions above are both false, it means we need to try and update
        // the user.
        return updateExistingUser(apiKey, firstName, lastName, organization);
    }

    /**
     * The addUser method will add a user to the user table and return that user as a json string.
     *
     * @param firstName
     * @param lastName
     * @param organization
     * @return
     */
    private Response newUser(String firstName, String lastName, String organization) {

        // All the parameters must be passed for a successful add
        if ((firstName == null || firstName.isEmpty()) ||
                (lastName == null || lastName.isEmpty()) ||
                (organization == null || organization.isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(BAD_REQUEST_MSG).build();
        }

        // Add the user and get the API key back
        String apiKey = addUser(firstName, lastName, organization);

        return userAsJson(apiKey);
    }

    /**
     * The updateUser method will update a user on the user table and return that updated user as a json string
     *
     * @param apiKey
     * @param firstName
     * @param lastName
     * @param organization
     * @return
     */
    private Response updateExistingUser(String apiKey, String firstName, String lastName, String organization) {
        if ((firstName == null || firstName.isEmpty()) ||
                (lastName == null || lastName.isEmpty()) ||
                (organization == null || organization.isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(BAD_REQUEST_MSG).build();
        }

        updateUser(apiKey, firstName, lastName, organization);

        return userAsJson(apiKey);
    }

    /**
     * The deleteUser method will delete a user from the user table
     *
     * @param apiKey
     * @return
     */
    private Response deleteUser(String apiKey) {

        int userId = getUserId(apiKey);

        if (userId == -1) {
            return Response.status(Response.Status.BAD_REQUEST).entity(BAD_REQUEST_MSG).build();
        }

        User user = (User)userDao.getById(userId);

        userDao.delete(user);

        return Response.status(Response.Status.OK).entity("Deleted").build();
    }

    /**
     * The returnUserAsJson method will go get the user details for the API Key passed and return
     * the user details in json format.
     * @param apiKey
     * @return
     */
    private Response userAsJson(String apiKey) {

        String jsonString  = getSpecificUser(apiKey);

        if (jsonString.isEmpty()) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(SERVICE_UNAVAILABLE_MSG).build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }


    /**
     * Add user
     *
     * @param lastName     the last name
     * @param firstName    the first name
     * @param organisation the organisation
     * @return the string
     */
    public String addUser(String firstName, String lastName, String organisation) {

        String apiKey = generateApiKey();

        User user = new User(apiKey, TRUE, LocalDateTime.now(), firstName, lastName, organisation);

        int id = userDao.insert(user);

        return apiKey;
    }

    /**
     * Update user
     *
     * @param apiKey       the API key
     * @param lastName     the last name
     * @param firstName    the first name
     * @param organisation the organisation
     */
    public void updateUser(String apiKey, String firstName, String lastName, String organisation) {

        int userId = getUserId(apiKey);

        User user = (User)userDao.getById(userId);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setOrganization(organisation);

        userDao.saveOrUpdate(user);
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
     * Get specific user.
     *
     * @param apiKey the api key
     * @return all users in json format
     */
    public String getSpecificUser(String apiKey) {

        if (!validApiKey(apiKey)) {
            return INVALID_API_KEY;
        }


        List<User> users = userDao.getByPropertyEqual("apiKey", apiKey);

        if (users.size() > 0) {
            return parseUserIntoJson(users);
        } else {
            return NOTHING_FOUND;
        }
    }


    /**
     * The getUserId method returns the id of the user for the API key passed
     * to the method.
     * @return id of the user. If user is not found, a value of -1 will be returned.
     */
    public int getUserId(String apiKey) {
        List<User> users = userDao.getByPropertyEqual("apiKey", apiKey);

        if (users.size() > 0) {
            return users.get(0).getId();
        } else {
            return -1;
        }
    }


    /**
     * The validateApiKey method will validate that the API key passed to it exists on the user table
     *
     * @param apiKey the api key
     * @return the boolean
     */
    public boolean validApiKey(String apiKey) {

        List<User> users = userDao.getByPropertyEqual("apiKey", apiKey);

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
