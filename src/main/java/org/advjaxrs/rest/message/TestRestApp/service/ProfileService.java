package org.advjaxrs.rest.message.TestRestApp.service;

import org.advjaxrs.rest.message.TestRestApp.database.DatabaseClass;
import org.advjaxrs.rest.message.TestRestApp.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService() {
        profiles.put("tom1", new Profile(1L, "tom1", "Tomas", "Tumasonis"));
        profiles.put("dom1", new Profile(2L, "dom1", "Domas", "Laureckis"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String pofileName) {
        return profiles.get(pofileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }

}
