package com.lunchcrunch.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class TopicApi {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final String INVALID_API_KEY = "Invalid Key";
    private static final String NOTHING_FOUND = "";

    GenericDao dao = new GenericDao(Topic.class);


    /**
     * The parseIntoJson method takes the List of Topic objects and parses them into a json string
     *
     * @param topics
     * @return
     */

    public String getAllTopics(String apiKey) {

        UserApi userApi = new UserApi();
        int id  =  userApi.getUserId(apiKey);
        if (id == -1) {
            return INVALID_API_KEY;
        }

        List<Topic> topics = (List<Topic>) dao.getAll();

        if (topics.size() > 0) {
            return parseObjectIntoJson(topics);
        } else {
            return NOTHING_FOUND;
        }

    }

    /**
     * The parseIntoJson method takes the List of Topic objects and parses them into a json string
     *
     * @param topics a list of topics that will be converted into a string
     * @return a string of json
     */
    private String parseObjectIntoJson(List<Topic> topics) {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        int jsonObjCount = 0;

        if (topics.size() == 0) {
            return "";
        }

        try {
            for (Topic thisTopic : topics) {
                jsonObjCount += 1;
                jsonString += mapper.writerWithDefaultPrettyPrinter().writeValueAsString(thisTopic);

                if (jsonObjCount < topics.size()) {
                    jsonString += ",";
                }
            }
            if (topics.size() > 1) {
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


