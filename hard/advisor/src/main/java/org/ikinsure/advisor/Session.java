package org.ikinsure.advisor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
     * checks if response format is correct
     * @param response response to valid
     * @return true if everything is okay
     */
    public boolean validateResponse(String response, boolean deep) {
        final boolean error = response != null && !response.isBlank() && !response.contains("error");
        if (deep) {
            try {
                new Gson().fromJson(response, Object.class);
                return error;
            } catch (JsonSyntaxException e) {
                return false;
            }
        }
        return error;
    }


    /**
     * sends a user authorization code
     * @return response as a String
     */
    public String sendAuthorizationRequest() {
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
    public boolean createAndRunRequestServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", exchange -> {
                String query = exchange.getRequestURI().getQuery();
                if (query != null && query.contains("code")) {
                    Config.AUTH_CODE.set(query.substring(5));
                    query = "Got the code. Return back to your program.";
                } else {
                    query = "Authorization code not found. Try again.";
                }
                exchange.sendResponseHeaders(200, query.length());
                exchange.getResponseBody().write(query.getBytes());
                exchange.getResponseBody().close();
            });
            server.start();
            while (Config.AUTH_CODE.get().isEmpty()) {
                Thread.sleep(10);
            }
            server.stop(10);
        } catch (IOException | InterruptedException e) {
            return false;
        }
        return true;
    }
}
