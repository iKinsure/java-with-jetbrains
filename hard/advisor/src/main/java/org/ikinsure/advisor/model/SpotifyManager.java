package org.ikinsure.advisor.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

    public Playlist[] createPlaylists(String data) {
        JsonObject json = new Gson().fromJson(data, JsonObject.class).getAsJsonObject("albums");
        JsonArray array = json.getAsJsonArray("items");
        Playlist[] playlists = new Playlist[array.size()];
        for (int i = 0; i < array.size(); i++) {
            playlists[i] = createPlaylist(array.get(i).getAsJsonObject());
        }
        return playlists;
    }

    private Playlist createPlaylist(JsonObject json) {
        String name = json.get("name").getAsString();
        String uri = json.get("external_urls").getAsJsonObject().get("spotify").getAsString();
        JsonArray array = json.getAsJsonArray("artists");
        String[] artists = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            artists[i] = array.get(i).getAsJsonObject().get("name").getAsString();
        }
        return new Playlist(name, uri, artists);
    }

    public Playlist[] createFeatured(String data) {
        JsonObject json = new Gson().fromJson(data, JsonObject.class).getAsJsonObject("playlists");
        JsonArray array = json.getAsJsonArray("items");
        Playlist[] playlists = new Playlist[array.size()];
        for (int i = 0; i < array.size(); i++) {
            String name = array.get(i).getAsJsonObject().get("name").getAsString();
            String uri = array.get(i).getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString();
            playlists[i] = new Playlist(name, uri, null);
        }
        return playlists;
    }
}
