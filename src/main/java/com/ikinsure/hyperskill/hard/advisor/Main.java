package com.ikinsure.hyperskill.hard.advisor;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String REDIRECT_URI = "http://localhost:8080/";
    private static final String ACCESS_TOKEN_URL = "https://accounts.spotify.com/api/token";
    private static final String CLIENT_ID = "6e70a3870fbe4d4bae4982467b032a2f";
    private static final String CLIENT_SECRET = "90f7f9f1909548aa8f3f6c5ce74e3e8c";
    private static final String AUTH = "https://accounts.spotify.com/authorize?response_type=code&client_id=6e70a3870fbe4d4bae4982467b032a2f&redirect_uri=http://localhost:8080/";

    private static String code = null;

    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (code == null) {
                if (!input.equals("auth") && !input.equals("exit")) {
                    System.out.println("Please, provide access for application.");
                    continue;
                }
            }
            switch (input) {
                case "auth":
                    System.out.println(auth() ? "---SUCCESS---" : "---FAILED---");
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

    private static boolean auth() throws IOException, InterruptedException {
        System.out.println("use this link to request the access code:");
        System.out.println(AUTH);
        System.out.println("waiting for code...");

        // create server
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);

        // context
        server.createContext("/",
                exchange -> {
                    String query = exchange.getRequestURI().getQuery();
                    if (query != null && query.startsWith("code")) {
                        code = query;
                        query = "Got the code. Return back to your program.";
                    } else {
                        query = "Authorization code not found. Try again.";
                    }
                    exchange.sendResponseHeaders(200, query.length());
                    exchange.getResponseBody().write(query.getBytes());
                    exchange.getResponseBody().close();
                }
        );

        // running
        server.start();
        while (code == null) {
            Thread.sleep(10);
        }
        server.stop(1);

        // correct code
        if (code.startsWith("code")) {

            // when received
            System.out.println("code received");
            System.out.println("making http request for access_token...");

            // create client with request
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .uri(URI.create(ACCESS_TOKEN_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "grant_type=authorization_code" +
                                    "&" + code +
                                    "&redirect_uri=" + REDIRECT_URI +
                                    "&client_id=" + CLIENT_ID +
                                    "&client_secret=" + CLIENT_SECRET))
                    .build();

            // show response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("response:");
            System.out.println(response.body());
            return !response.body().contains("error");
        }
        return false;
    }
}

