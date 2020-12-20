package org.ikinsure.advisor.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * class represents Spotify album
 */
public class Album extends Playlist {

    private final List<String> artists;

    public Album(String name, List<String> artists, String uri) {
        super(name, uri);
        this.artists = artists;
    }

    private String getArtists() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < artists.size(); i++) {
            builder.append(artists.get(i));
            if (i + 1 < artists.size()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public void print() {
        System.out.println(getName() + "\n" + getArtists() + "\n" + getUri() + "\n");
    }

    public static class Parser implements Parseable {
        @Override
        public List<? extends Printable> parse(String data) {
            JsonArray items = new Gson().fromJson(data, JsonObject.class).getAsJsonObject("albums").getAsJsonArray("items");
            List<Album> albums = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                JsonObject item = items.get(i).getAsJsonObject();
                JsonArray artists = item.getAsJsonArray("artists");
                List<String> artistsNames = new ArrayList<>();
                for (int j = 0; j < artists.size(); j++) {
                    artistsNames.add(artists.get(j).getAsJsonObject().get("name").getAsString());
                }
                albums.add(new Album(item.get("name").getAsString(), artistsNames,
                        item.get("external_urls").getAsJsonObject().get("spotify").getAsString()));
            }
            return albums;
        }
    }
}
