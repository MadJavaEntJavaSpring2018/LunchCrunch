package com.lunchcrunch.persistence;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * This class allows data to the parsed into Json.
 */

public class JsonParser<T> {

    private Class<T> type;

    private final Logger logger = LogManager.getLogger(this.getClass());

    public JsonParser(Class<T> type) {
        this.type = type;
    }

    /**
     * The parseObjectIntoJson method takes the List of objects and parses them into a json string
     *
     * @param objects a list of topics that will be converted into a string
     * @return a string of json
     */
    public String parseObjectIntoJson(List<T> objects) {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        int jsonObjCount = 0;

        if (objects.size() == 0) {
            return "";
        }

        try {
            for (T thisObject : objects) {
                jsonObjCount += 1;
                jsonString += mapper.writerWithDefaultPrettyPrinter().writeValueAsString(thisObject);

                if (jsonObjCount < objects.size()) {
                    jsonString += ",";
                }
            }
            if (objects.size() > 1) {
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



