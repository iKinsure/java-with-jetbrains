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

    private final Session session;
    private final SpotifyManager manager;
    private final Scanner scanner;
    private final MenuController view;

    public Client() {

        this.session = new Session();
        this.manager = new SpotifyManager();
        this.scanner = new Scanner(System.in);
        this.view = new MenuController("---GOODBYE!---", scanner);

        view.run(new Menu.Builder()
                .addItem("auth", this::authDecision)
                .addItem("new", this::newDecision)
                .addItem("featured", this::featuredDecision)
                .addItem("categories", this::categoriesDecision)
                .addItem("playlists", this::playlistsDecision)
                .addItem("exit", view::exitAll)
                .addItem("invalid", () -> System.out.println("Invalid option. Try again."))
                .build());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private void authDecision() {
        if (authenticate()) {
            String response = session.sendApiGetRequest(Config.getAccess());
            if (session.validateResponse(response, false)) {
                User user = manager.createUser(response);
                System.out.println("Success!");
            } else {
                System.out.println("Failed to get access!");
            }
        } else {
            Config.AUTH_CODE.set("");
            System.out.println("Failed to authenticate!");
        }
    }

    private void newDecision() {
        if (isActive()) {
            String response = session.sendApiGetRequest(Config.getNewReleasesUri(7));
            if (session.validateResponse(response, false)) {
                Playlist[] playlists = manager.createPlaylists(response);
                for (var playlist : playlists) {
                    System.out.println(playlist.getName());
                    System.out.println(playlist.getArtists());
                    System.out.println(playlist.getUri() + "\n");
                }
            } else {
                System.out.println("Failed to get response!");
            }
        }
    }

    private void featuredDecision() {
        if (isActive()) {
            String response = session.sendApiGetRequest(Config.getFeaturedPlaylistsUri(7));
            if (session.validateResponse(response, false)) {
                Playlist[] playlists = manager.createFeatured(response);
                for (var playlist : playlists) {
                    System.out.println(playlist.getName());
                    System.out.println(playlist.getUri() + "\n");
                }
            } else {
                System.out.println("Failed to get response!");
            }
        }
    }

    private void categoriesDecision() {
        if (isActive()) {
            String response = session.sendApiGetRequest(Config.getCategoriesUri(15));
            if (session.validateResponse(response, false)) {
                JsonObject json = new Gson().fromJson(response, JsonObject.class).getAsJsonObject("categories");
                JsonArray array = json.getAsJsonArray("items");
                for (int i = 0; i < array.size(); i++) {
                    System.out.println(array.get(i).getAsJsonObject().get("name").getAsString());
                }
            } else {
                System.out.println("Failed to get response!");
            }
        }
    }

    private void playlistsDecision() {
        String category = view.getLastInput().substring(10).toLowerCase();
        category = category.replace(" ", "");
        if (isActive()) {
            String response = session.sendApiGetRequest(Config.getPlaylist(category, 5));
            if (session.validateResponse(response, false)) {
                Playlist[] playlists = manager.createFeatured(response);
                for (var playlist : playlists) {
                    System.out.println(playlist.getName());
                    System.out.println(playlist.getUri() + "\n");
                }
            } else {
                System.out.println("Unknown category name.");
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Spotify user authentication with OAuth 2.0
     * @return true if success
     */
    private boolean authenticate() {
        System.out.println("use this link to request the access code:\n" +
                Config.getPermissionUri() + "\n" +
                "waiting for code...");
        session.createAndRunRequestServer();
        System.out.println("code received\n" +
                "making http request for access_token... ");
        String response = session.sendAuthorizationRequest();
        if (session.validateResponse(response, false)) {
            JsonObject json = new Gson().fromJson(response, JsonObject.class);
            Config.ACCESS_TOKEN.set(json.get("access_token").getAsString());
            return true;
        }
        return false;
    }

    /**
     * checks if app has a permission
     * @return true if there is permission
     */
    public boolean isActive() {
        if (Config.AUTH_CODE.get().isEmpty()) {
            System.out.println("Please, provide access for application.");
            return false;
        }
        return true;
    }
}
