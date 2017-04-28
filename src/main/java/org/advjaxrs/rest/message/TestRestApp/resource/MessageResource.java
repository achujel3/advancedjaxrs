package org.advjaxrs.rest.message.TestRestApp.resource;

import org.advjaxrs.rest.message.TestRestApp.model.Message;
import org.advjaxrs.rest.message.TestRestApp.service.MessageService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(id);
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentResource.class)
                .resolveTemplate("messageId", message.getId())
                .build();
        return uri.toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build();
        return uri.toString();
    }

    private String getUriForSelf(@Context UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
    }

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@QueryParam("year") int year,
                                     @QueryParam("start") int start,
                                     @QueryParam("size") int size
    ) {
        if (year > 0) {
            return messageService.getAllMessagesForYear(year);
        }
        if (start >= 0 && size > 0) {
            return messageService.getAllMessagesPaginated(start, size);
        }
        return messageService.getAllMessages();
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(newMessage).build();
    }

    @PUT
    @Path("/{messageId}")
    public Message removeMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void updateMessage(@PathParam("messageId") long id, Message message) {
        messageService.removeMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }


}
