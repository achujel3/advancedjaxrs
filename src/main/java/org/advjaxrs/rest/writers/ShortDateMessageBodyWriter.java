package org.advjaxrs.rest.writers;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

@Provider
@Produces("text/shortdate")
public class ShortDateMessageBodyWriter implements MessageBodyWriter<Date> {


    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return Date.class.isAssignableFrom(aClass);
    }

    @Override
    public void writeTo(Date date, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream)
            throws IOException, WebApplicationException {
        String shortdate = date.getYear() + "." + date.getMonth() + "." + date.getDay();
        outputStream.write(shortdate.getBytes());
    }

    @Override
    public long getSize(Date date, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }
}
