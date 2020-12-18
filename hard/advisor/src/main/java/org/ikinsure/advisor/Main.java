package org.ikinsure.advisor;

import com.sun.net.httpserver.HttpServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Config.matchArguments(args);

        // main loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (Config.AUTH_CODE.get().isEmpty()) {
                if (!input.equals("auth") && !input.equals("exit")) {
                    System.out.println("Please, provide access for application.");
                    continue;
                }
            }
            switch (input) {
                case "auth":
                    System.out.println(auth() ? "---SUCCESS---" : "Server error");
                    break;
                case "new":
                    System.out.println("---NEW RELEASES---\n" +
                            "Mountains [Sia, Diplo, Labrinth]\n" +
                            "Runaway [Lil Peep]\n" +
                            "The Greatest Show [Panic! At The Disco]\n" +
                            "All Out Life [Slipknot]");
                    break;
                case "featured":
                    System.out.println("---FEATURED---\n" +
                            "Mellow Morning\n" +
                            "Wake Up and Smell the Coffee\n" +
                            "Monday Motivation\n" +
                            "Songs to Sing in the Shower");
                    break;
                case "categories":
                    System.out.println("---CATEGORIES---\n" +
                            "Top Lists\n" +
                            "Pop\n" +
                            "Mood\n" +
                            "Latin");
                    break;
                case "playlists mood":
                    System.out.println("---MOOD PLAYLISTS---\n" +
                            "Walk Like A Badass  \n" +
                            "Rage Beats  \n" +
                            "Arab Mood Booster  \n" +
                            "Sunday Stroll");
                    break;
                default:
                    System.out.println("---GOODBYE!---");
                    return;

            }
        }
    }

    private static String createPermissionUri() {
        return Config.ACCESS + "/authorize" +
                "?client_id=" + Config.CLIENT_ID +
                "&redirect_uri=" + Config.REDIRECT_URI +
                "&response_type=code";
    }

    private static boolean auth() throws IOException, InterruptedException {

        // uri
        String uri = createPermissionUri();
        System.out.println("use this link to request the access code:\n" +
                uri + "\n" +
                "waiting for code...");

        // create server
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);

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
            Thread.sleep(10);
        }
        server.stop(1);

        // when received
        System.out.println("code received\n" +
                "making http request for access_token... ");

        // create client with request
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(Config.ACCESS + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code" +
                                                                "&code=" + Config.AUTH_CODE +
                                                                "&redirect_uri=" + Config.REDIRECT_URI +
                                                                "&client_id=" + Config.CLIENT_ID +
                                                                "&client_secret=" + Config.SECRET))
                .build();

        // show response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response:");
        System.out.println(response.body());
        return !response.body().contains("error");
    }
}

