package org.advjaxrs.rest.message.TestRestApp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("header") String headerParam,
                                            @CookieParam("cookieParam") String cookieParam) {
        return "Matrix param: " + matrixParam + "\n" +
                "Header param: " + headerParam + "\n" +
                "Cookie param: " + cookieParam;
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
        String path = uriInfo.getAbsolutePath().toString();
        String cookies = httpHeaders.getCookies().toString();
        return "Path: " + path + "\nCookies: " + cookies;
    }

}
