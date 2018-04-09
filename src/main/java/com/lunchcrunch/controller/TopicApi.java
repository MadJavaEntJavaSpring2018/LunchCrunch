package com.lunchcrunch.controller;

import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.persistence.GenericDao;
import com.lunchcrunch.persistence.JsonParser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The TopicApi class contains methods needed by the LunchCrunch RestService class to
 * read on the topic table and return that data is json format.
 */

public class TopicApi {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final String INVALID_API_KEY = "Invalid Key";
    private static final String NOTHING_FOUND = "";

    GenericDao dao        = new GenericDao(Topic.class);
    JsonParser jsonParser = new JsonParser(Topic.class);



    /**
     * The parseIntoJson method takes the List of Topic objects and parses them into a json string
     *
     * @param apiKey the key that allows the user to use the LunchCrunch Service
     * @return a string of json containing all of the topics
     */

    public String getAllTopics(String apiKey) {

        UserApi userApi = new UserApi();
        int id  =  userApi.getUserId(apiKey);
        if (id == -1) {
            return INVALID_API_KEY;
        }

        List<Topic> topics = (List<Topic>) dao.getAll();

        if (topics.size() > 0) {
            return jsonParser.parseObjectIntoJson(topics);
        } else {
            return NOTHING_FOUND;
        }

    }

}


