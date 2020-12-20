package org.ikinsure.advisor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.ikinsure.advisor.model.*;
import org.ikinsure.advisor.view.Menu;
import org.ikinsure.advisor.view.MenuController;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Client {

    private final Session session; // connection with API class
    private final Scanner scanner; // input handler
    private final MenuController view; // menu manager
    private List<? extends Printable> list; // contains request response
    private int pagePointer = 0; // current page

    public Client() {
        this.session = Session.getInstance();
        this.scanner = new Scanner(System.in);
        this.view = new MenuController("---GOODBYE!---", scanner);
        view.run(new Menu.Builder()
                .addItem("auth", this::auth)
                .addItem("new", this::newReleases)
                .addItem("featured", this::featured)
                .addItem("categories", this::categories)
                .addItem("playlists", this::playlists)
                .addItem("prev", this::prev)
                .addItem("next", this::next)
                .addItem("exit", view::exitAll)
                .addItem("invalid", () -> System.out.println("Invalid option. Try again."))
                .build());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private void auth() {
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
            response = session.sendApiGetRequest(Config.RESOURCE + "/v1/me");
            flag = ResponseFlag.validate(response);
            if (flag == ResponseFlag.ERROR) {
                System.out.println(getErrorMessage(response));
            } else if (flag == ResponseFlag.EXCEPTION) {
                System.out.println("Failed to get API access!");
            } else {
                System.out.println("Success!");
            }
        }
    }

    private void newReleases() {
        execute(Config.RESOURCE + "/v1/browse/new-releases", new Album.Parser());
    }

    private void featured() {
        execute(Config.RESOURCE + "/v1/browse/featured-playlists", new Playlist.Parser());
    }

    private void categories() {
        execute(Config.RESOURCE + "/v1/browse/categories", new Category.Parser());
    }

    private void playlists() {
        String id = view.getLastInput().substring(10).toLowerCase().replace(" ", "");
        execute(Config.RESOURCE + "/v1/browse/categories/" + id + "/playlists", new Playlist.Parser());
    }

    private void next() {
        if (isActive()) {
            if (list == null || pagePointer + 1 >= (list.size() / Integer.parseInt(Config.PAGE.get()))) {
                System.out.println("No more pages.");
            } else {
                pagePointer++;
                showPage(pagePointer);
            }
        }
    }

    private void prev() {
        if (isActive()) {
            if (list == null || pagePointer - 1 < 0) {
                System.out.println("No more pages.");
            } else {
                pagePointer--;
                showPage(pagePointer);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method gets response and checks content
     * @param request uri for Spotify resource
     * @param parser method used to parse information
     */
    private void execute(String request, Parseable parser) {
        if (isActive()) {
            String response = session.sendApiGetRequest(request);
            ResponseFlag flag = ResponseFlag.validate(response);
            if (flag == ResponseFlag.ERROR) {
                System.out.println(getErrorMessage(response));
            } else if (flag == ResponseFlag.EXCEPTION) {
                System.out.println("Failed to get response!");
            } else {
                list = parser.parse(response);
                pagePointer = -1;
                next();
            }
        }
    }

    /**
     * prints a page content
     * @param page number, first index is 0
     */
    private void showPage(int page) {
        final int items = Integer.parseInt(Config.PAGE.get());
        final int from = page * items;
        final int to = from + items;
        IntStream.range(from, to).forEach(i -> list.get(i).print());
        System.out.println("---PAGE " + to / items + " OF " + list.size() / items + "---");
    }

    /**
     * @param response json response as String
     * @return error message retrieved form response
     */
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
                getPermissionUri() + "\n" +
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

    /**
     * creates permission link for user
     * @return link as String
     */
    private String getPermissionUri() {
        return Config.ACCESS + "/authorize" + "?client_id=" + Config.CLIENT_ID +
                "&redirect_uri=" + Config.REDIRECT_URI + "&response_type=code";
    }
}
