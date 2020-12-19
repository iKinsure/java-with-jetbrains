package org.ikinsure.advisor.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;

public class SpotifyManager {

    public User createUser(String data) {
        JsonObject json = new Gson().fromJson(data, JsonObject.class);
        String name = json.get("display_name").getAsString();
        String openAccount = json.get("external_urls").getAsJsonObject().get("spotify").getAsString();
        int followers = json.get("followers").getAsJsonObject().get("total").getAsInt();
        String apiAccount = json.get("href").getAsString();
        String id = json.get("id").getAsString();
        String image = json.get("images").getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString();
        return new User(name, openAccount, followers, apiAccount, id, image);
    }
}
