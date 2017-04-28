package org.advjaxrs.rest.message.TestRestApp.database;


import org.advjaxrs.rest.message.TestRestApp.model.Message;
import org.advjaxrs.rest.message.TestRestApp.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<Long, Message>();
    private static Map<String, Profile> profiles = new HashMap<String, Profile>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
