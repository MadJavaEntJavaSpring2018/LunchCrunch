package com.lunchcrunch.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;

public class TopicApi {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public String getAllTopics() {

        GenericDao genericDao = new GenericDao(Topic.class);
        List<Topic> topics    = (List<Topic>)genericDao.getAll();

        ObjectMapper mapper         = new ObjectMapper();
        String       jasonOutput    = "[";
        int          count          = 0;
        try {
            for (Topic index : topics) {
                count = count + 1;
                if (count == topics.size()) {
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

    public String addTopic(String description) {

        GenericDao topicDao     = new GenericDao(Topic.class);

        Topic newTopic = new Topic(description);

        topicDao.insert(newTopic);

        return "ok";

    }

}


