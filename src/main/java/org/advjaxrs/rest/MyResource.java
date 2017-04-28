package org.advjaxrs.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("{pathParam}/test")
public class MyResource {

    private int count;
    @PathParam("pathParam")
    private String pathParamExample;
    @QueryParam("queryParam")
    private String queryParamExample;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
        return "The count is: " + count++
                + "\nPath param: " + pathParamExample
                + "\nQuery param: " + queryParamExample;
    }

}
