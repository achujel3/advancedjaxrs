package org.advjaxrs.rest.message.TestRestApp.exception;

import org.advjaxrs.rest.message.TestRestApp.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage errorMessage = new ErrorMessage(throwable.getMessage(), 404, "http://www.google.com");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
