package org.ikinsure.advisor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpServer;
import org.ikinsure.advisor.model.SpotifyManager;
import org.ikinsure.advisor.model.User;
import org.ikinsure.advisor.view.Menu;
import org.ikinsure.advisor.view.MenuController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Client {

    private final MenuController view;
    private final Scanner scanner;
    private final Session session;


    public Client() {

        this.view = new MenuController();
        this.scanner = new Scanner(System.in);
        this.session = new Session();

        view.run(new Menu.Builder()
                .setScanner(scanner)
                .addItem("auth", this::authorize)
                .addItem("new", this::newMusic)
                .addItem("featured", this::featured)
                .addItem("categories", this::categories)
                .addItem("playlists mood", this::playlistsMood)
                .addItem("exit", view::exitAll)
                .build());

    }

    private void authorize() {
        System.out.println(session.auth() ? "---SUCCESS---" : "---ERROR---");
    }

    private void newMusic() {
        if (session.isActive()) {

        }
    }

    private void featured() {
        if (session.isActive()) {

        }
    }

    private void categories() {
        if (session.isActive()) {
            HttpResponse<String> response = session.sendPlaylistsRequest();
            System.out.println(response.body());
        }
    }

    private void playlistsMood() {
        if (session.isActive()) {

        }
    }


}
