package org.advjaxrs.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.Date;

@Path("testTwo")
public class MyResourceTwo {

    @GET
    @Produces(value = {MediaType.TEXT_PLAIN, "text/shortdate"})
    public Date testMethod() {
        System.out.println("Got here");
        return Calendar.getInstance().getTime();
    }

}
