package org.advjaxrs.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.Date;

@Path("testTwo")
public class MyResourceTwo {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Date testMethod() {
        System.out.println("Got here");
        return Calendar.getInstance().getTime();
    }

}
