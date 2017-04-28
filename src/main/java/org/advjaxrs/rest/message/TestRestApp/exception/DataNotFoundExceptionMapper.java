package org.advjaxrs.rest.message.TestRestApp.exception;


import org.advjaxrs.rest.message.TestRestApp.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "http://www.google.com");
        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}
