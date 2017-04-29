package org.advjaxrs.rest.client;

import org.advjaxrs.rest.message.TestRestApp.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestApiClient {

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();

        WebTarget baseTarget = client.target("http://localhost:8080/webapi/");
        WebTarget messagesTarget = baseTarget.path("messages");
        WebTarget singleMessageTarget = messagesTarget.path("{messageId}");

        Message message1 = singleMessageTarget
                .resolveTemplate("messageId", "1")
                .request(MediaType.APPLICATION_JSON)
                .get(Message.class);

        Message message2 = singleMessageTarget
                .resolveTemplate("messageId", "2")
                .request(MediaType.APPLICATION_JSON)
                .get(Message.class);

        Message newMessage = new Message(4L, "My new messge from JAX-RS client", "Tom");
        Response postResponse = messagesTarget
                .request()
                .post(Entity.json(newMessage));
        if (postResponse.getStatus() != 201) {
            System.out.println("Error");
        }

        Message createdMessage = postResponse.readEntity(Message.class);
        System.out.println(createdMessage.toString());

        System.out.println(message1.getMessage());
        System.out.println(message2.getMessage());

    }

}
