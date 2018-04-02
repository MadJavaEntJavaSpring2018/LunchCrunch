package com.lunchcrunch.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Appointment;
import com.lunchcrunch.entity.Location;
import com.lunchcrunch.entity.Topic;
import com.lunchcrunch.entity.User;
import com.lunchcrunch.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Path("/topics")
public class TopicApi {

    private final Logger logger = LogManager.getLogger(this.getClass());

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "json"
    @Produces("application/json")

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

    public String addAppointment(String description) {

        GenericDao topicDao     = new GenericDao(Topic.class);

        Topic newTopic = new Topic(description);

        int id = topicDao.insert(newTopic);

        return "ok";
    }

}


