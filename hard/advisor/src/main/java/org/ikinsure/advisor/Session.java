package org.ikinsure.advisor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Session {

    private final HttpClient client;

    public Session() {
        this.client = HttpClient.newBuilder().build();
    }

    public boolean auth() {
        String uri = Config.getPermissionUri();
        System.out.println("use this link to request the access code:\n" + uri + "\n" + "waiting for code...");
        createAndRunRequestServer();
        System.out.println("code received\n" + "making http request for access_token... ");
        String response = sendAuthorizationRequest();
        if (response == null || response.contains("error")) {
            return false;
        }
        JsonObject json = new Gson().fromJson(response, JsonObject.class);
        Config.ACCESS_TOKEN.set(json.get("access_token").getAsString());
        return true;
    }

    public String sendApiGetRequest(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Config.ACCESS_TOKEN)
                .uri(URI.create(uri))
                .GET()
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            return "";
        }
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
     * sends a user authorization code
     * @return response as String
     */
    private String sendAuthorizationRequest() {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(Config.getApiToken()))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code" +
                        "&code=" + Config.AUTH_CODE +
                        "&redirect_uri=" + Config.REDIRECT_URI +
                        "&client_id=" + Config.CLIENT_ID +
                        "&client_secret=" + Config.SECRET))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            return "";
        }
    }

    /**
     * runs local server and waits for authorization permission from user
     * @return true if no exception
     */
    private boolean createAndRunRequestServer() {
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
            server.createContext("/", exchange -> {
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
            server.start();
            while (Config.AUTH_CODE.get().isEmpty()) {
                Thread.sleep(10);
            }
            server.stop(1);
        } catch (IOException | InterruptedException e) {
            return false;
        }
        return true;
    }
}
