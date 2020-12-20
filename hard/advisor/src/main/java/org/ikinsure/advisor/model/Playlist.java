package org.ikinsure.advisor.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * class represents Spotify playlist
 */
public class Playlist implements Printable {

    private final String name;
    private final String uri;

    public Playlist(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    @Override
    public void print() {
        System.out.println(name + "\n" + uri + "\n");
    }

    public static class Parser implements Parseable {
        @Override
        public List<? extends Printable> parse(String data) {
            JsonObject json = new Gson().fromJson(data, JsonObject.class).getAsJsonObject("playlists");
            JsonArray array = json.getAsJsonArray("items");
            List<Playlist> playlists = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                playlists.add(new Playlist(array.get(i).getAsJsonObject().get("name").getAsString(),
                        array.get(i).getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString()));
            }
            return playlists;
        }
    }
}
