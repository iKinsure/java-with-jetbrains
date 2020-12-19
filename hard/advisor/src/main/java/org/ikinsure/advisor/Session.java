package org.ikinsure.advisor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpServer;
import org.ikinsure.advisor.model.SpotifyManager;
import org.ikinsure.advisor.model.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Session {

    private final HttpClient client;
    private User user;
    private final SpotifyManager manager;

    public Session() {
        this.client = HttpClient.newBuilder().build();
        this.manager = new SpotifyManager();
    }

    public boolean auth() {

        // uri
        String uri = createPermissionUri();
        System.out.println("use this link to request the access code:\n" +
                uri + "\n" +
                "waiting for code...");

        // create server
        HttpServer server = null;
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            return false;
        }


        // context
        server.createContext("/",
                exchange -> {
                    String query = exchange.getRequestURI().getQuery();
                    if (query == null) {
                        query = "Processing...";
                    } else if (query.startsWith("code")) {
                        Config.AUTH_CODE.set(query.substring(5));
                        query = "Got the code. Return back to your program.";
                    } else {
                        query = "Authorization code not found. Try again.";
                        Config.AUTH_CODE.set(query);
                    }
                    exchange.sendResponseHeaders(200, query.length());
                    exchange.getResponseBody().write(query.getBytes());
                    exchange.getResponseBody().close();
                });

        // running
        server.start();
        while (Config.AUTH_CODE.get().isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return false;
            }
        }
        server.stop(1);

        // when received
        System.out.println("code received\n" + "making http request for access_token... ");

        HttpResponse<String> response = sendAuthorizationRequest();
        if (response == null || response.body().contains("error")) {
            return false;
        }
        JsonObject json = new Gson().fromJson(response.body(), JsonObject.class);
        Config.ACCESS_TOKEN.set(json.get("access_token").getAsString());

        response = sendAccessRequest();
        user = manager.createUser(response.body());
        return true;
    }

    private HttpResponse<String> sendAuthorizationRequest() {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(Config.ACCESS + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code" +
                        "&code=" + Config.AUTH_CODE +
                        "&redirect_uri=" + Config.REDIRECT_URI +
                        "&client_id=" + Config.CLIENT_ID +
                        "&client_secret=" + Config.SECRET))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    public HttpResponse<String> sendAccessRequest() {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Config.ACCESS_TOKEN)
                .uri(URI.create("https://api.spotify.com/v1/me"))
                .GET()
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    public HttpResponse<String> sendPlaylistsRequest() {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Config.ACCESS_TOKEN)
                .uri(URI.create(user.getApiAccount() + "/playlists"))
                .GET()
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    private String createPermissionUri() {
        return Config.ACCESS + "/authorize" +
                "?client_id=" + Config.CLIENT_ID +
                "&redirect_uri=" + Config.REDIRECT_URI +
                "&response_type=code";
    }

    public boolean isActive() {
        if (Config.AUTH_CODE.get().isEmpty()) {
            System.out.println("Please, provide access for application.");
            return false;
        }
        return true;
    }
}
