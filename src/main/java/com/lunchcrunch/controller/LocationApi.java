package com.lunchcrunch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchcrunch.entity.Location;
import com.lunchcrunch.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/locations")
public class LocationApi {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "json"
    @Produces("application/json")

    public Response getAllLocations() throws Exception {

        GenericDao genericDao = new GenericDao(Location.class);
        List<Location> locations = (List<Location>)genericDao.getAll();

        ObjectMapper mapper         = new ObjectMapper();
        String       jasonOutput    = "[";
        int          count          = 0;

        for (Location index : locations)  {
            count = count + 1;
            if (count == locations.size()) {
                jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + "]";
            } else {
                jasonOutput = jasonOutput + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(index) + ",";
            }
        }
        validApiKey();
        //return Response.status(200).entity(output).build();
        return Response.ok(jasonOutput, MediaType.APPLICATION_JSON).build();
    }
    public Response validApiKey() {
        String apiKey = "";
        UserApi userApi = new UserApi();
        Boolean jasonOutput = Boolean.FALSE;
        userApi.validApiKey(apiKey);
        //return Response.status(200).entity(output).build();
        return Response.ok(jasonOutput, MediaType.APPLICATION_JSON).build();
    }
}


