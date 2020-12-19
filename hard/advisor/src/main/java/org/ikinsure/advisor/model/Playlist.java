package org.ikinsure.advisor.model;

public class Playlist {

    private final String name;
    private final String uri;
    private final String[] artists;

    public Playlist(String name, String uri, String[] artists) {
        this.name = name;
        this.uri = uri;
        this.artists = artists;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public String getArtists() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < artists.length; i++) {
            builder.append(artists[i]);
            if (i + 1 < artists.length) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
