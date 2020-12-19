package org.ikinsure.advisor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.ikinsure.advisor.model.Playlist;
import org.ikinsure.advisor.model.SpotifyManager;
import org.ikinsure.advisor.model.User;
import org.ikinsure.advisor.view.Menu;
import org.ikinsure.advisor.view.MenuController;
import java.util.Scanner;

public class Client {

    private final MenuController view;
    private final Scanner scanner;
    private final Session session;
    private final SpotifyManager manager;

    public Client() {

        this.view = new MenuController();
        this.scanner = new Scanner(System.in);
        this.session = new Session();
        this.manager = new SpotifyManager();

        view.run(new Menu.Builder()
                .setScanner(scanner)
                .addItem("auth", this::authorize)
                .addItem("new", this::newMusic)
                .addItem("featured", this::featured)
                .addItem("categories", this::categories)
                .addItem("playlists", this::playlistsMood)
                .addItem("exit", view::exitAll)
                .build());
    }

    private void authorize() {
        if (session.auth()) {
            String response = session.sendApiGetRequest(Config.getAccess());
            User user = manager.createUser(response);
            System.out.println("Success!");
        } else {
            Config.AUTH_CODE.set("");
            System.out.println("Failed!");
        }
    }

    private void newMusic() {
        if (session.isActive()) {
            String response = session.sendApiGetRequest(Config.getNewReleasesUri(7));
            Playlist[] playlists = manager.createPlaylists(response);
            for (var playlist : playlists) {
                System.out.println(playlist.getName());
                System.out.println(playlist.getArtists());
                System.out.println(playlist.getUri() + "\n");
            }
        }
    }

    private void featured() {
        if (session.isActive()) {
            String response = session.sendApiGetRequest(Config.getFeaturedPlaylistsUri(7));
            Playlist[] playlists = manager.createFeatured(response);
            for (var playlist : playlists) {
                System.out.println(playlist.getName());
                System.out.println(playlist.getUri() + "\n");
            }
        }
    }

    private void categories() {
        if (session.isActive()) {
            String response = session.sendApiGetRequest(Config.getCategoriesUri(15));
            JsonObject json = new Gson().fromJson(response, JsonObject.class).getAsJsonObject("categories");
            JsonArray array = json.getAsJsonArray("items");
            for (int i = 0; i < array.size(); i++) {
                System.out.println(array.get(i).getAsJsonObject().get("name").getAsString());
            }
        }
    }

    private void playlistsMood() {
        String category = scanner.next().toLowerCase();
        if (session.isActive()) {
            String response = session.sendApiGetRequest(Config.getPlaylist(category, 5));
            if (response.contains("error")) {
                System.out.println("Unknown category name.");
            } else {
                Playlist[] playlists = manager.createFeatured(response);
                for (var playlist : playlists) {
                    System.out.println(playlist.getName());
                    System.out.println(playlist.getUri() + "\n");
                }
            }
        }
    }
}
