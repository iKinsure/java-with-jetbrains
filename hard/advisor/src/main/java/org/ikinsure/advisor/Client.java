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

    private enum ResponseFlag {
        CORRECT, EXCEPTION, ERROR;

        static ResponseFlag validate(String response) {
            if (response == null || response.isBlank()) {
                return ResponseFlag.EXCEPTION;
            } else if (response.contains("error")) {
                return ResponseFlag.ERROR;
            }
            return ResponseFlag.CORRECT;
        }
    }

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
        String response = authenticate();
        ResponseFlag flag = ResponseFlag.validate(response);
        if (flag == ResponseFlag.ERROR) {
            Config.AUTH_CODE.set("");
            System.out.println(getErrorMessage(response));
        } else if (flag == ResponseFlag.EXCEPTION) {
            Config.AUTH_CODE.set("");
            System.out.println("Failed to authenticate!");
        } else {
            JsonObject json = new Gson().fromJson(response, JsonObject.class);
            Config.ACCESS_TOKEN.set(json.get("access_token").getAsString());
            response = session.sendApiGetRequest(Config.getAccess());
            flag = ResponseFlag.validate(response);
            if (flag == ResponseFlag.ERROR) {
                System.out.println(getErrorMessage(response));
            } else if (flag == ResponseFlag.EXCEPTION) {
                System.out.println("Failed to get API access!");
            } else {
                User user = manager.createUser(response);
                System.out.println("Success!");
            }
        }
    }

    private void newDecision() {
        if (isActive()) {
            String response = session.sendApiGetRequest(Config.getNewReleasesUri(7));
            ResponseFlag flag = ResponseFlag.validate(response);
            if (flag == ResponseFlag.ERROR) {
                System.out.println(getErrorMessage(response));
            } else if (flag == ResponseFlag.EXCEPTION) {
                System.out.println("Failed to get response!");
            } else {
                Playlist[] playlists = manager.createPlaylists(response);
                for (var playlist : playlists) {
                    System.out.println(playlist.getName());
                    System.out.println(playlist.getArtists());
                    System.out.println(playlist.getUri() + "\n");
                }
            }
        }
    }

    private void featuredDecision() {
        if (isActive()) {
            String response = session.sendApiGetRequest(Config.getFeaturedPlaylistsUri(7));
            ResponseFlag flag = ResponseFlag.validate(response);
            if (flag == ResponseFlag.ERROR) {
                System.out.println(getErrorMessage(response));
            } else if (flag == ResponseFlag.EXCEPTION) {
                System.out.println("Failed to get response!");
            } else {
                Playlist[] playlists = manager.createFeatured(response);
                for (var playlist : playlists) {
                    System.out.println(playlist.getName());
                    System.out.println(playlist.getUri() + "\n");
                }
            }
        }
    }

    private void categoriesDecision() {
        if (isActive()) {
            String response = session.sendApiGetRequest(Config.getCategoriesUri(15));
            ResponseFlag flag = ResponseFlag.validate(response);
            if (flag == ResponseFlag.ERROR) {
                System.out.println(getErrorMessage(response));
            } else if (flag == ResponseFlag.EXCEPTION) {
                System.out.println("Failed to get response!");
            } else {
                JsonObject json = new Gson().fromJson(response, JsonObject.class).getAsJsonObject("categories");
                JsonArray array = json.getAsJsonArray("items");
                for (int i = 0; i < array.size(); i++) {
                    System.out.println(array.get(i).getAsJsonObject().get("name").getAsString());
                }
            }
        }
    }

    private void playlistsDecision() {
        String category = view.getLastInput().substring(10).toLowerCase();
        //String category = view.getLastInput().split("\\s+")[1].toLowerCase(); // for test?
        category = category.replace(" ", "");
        if (isActive()) {
            String response = session.sendApiGetRequest(Config.getPlaylist(category, 5));
            ResponseFlag flag = ResponseFlag.validate(response);
            if (flag == ResponseFlag.ERROR) {
                System.out.println(getErrorMessage(response));
            } else if (flag == ResponseFlag.EXCEPTION) {
                System.out.println("Failed to get response!");
            } else {
                Playlist[] playlists = manager.createFeatured(response);
                for (var playlist : playlists) {
                    System.out.println(playlist.getName());
                    System.out.println(playlist.getUri() + "\n");
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private String getErrorMessage(String response) {
        JsonObject json = new Gson().fromJson(response, JsonObject.class);
        return json.get("error").getAsJsonObject().get("message").getAsString();
    }

    /**
     * Spotify user authentication with OAuth 2.0
     * @return true if success
     */
    private String authenticate() {
        System.out.println("use this link to request the access code:\n" +
                Config.getPermissionUri() + "\n" +
                "waiting for code...");
        session.createAndRunRequestServer();
        System.out.println("code received\n" +
                "making http request for access_token... ");
        return session.sendAuthorizationRequest();
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
