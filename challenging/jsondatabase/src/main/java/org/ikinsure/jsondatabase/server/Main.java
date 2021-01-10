package org.ikinsure.jsondatabase.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Entry point
 */
public class Main {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Server server = new Server(34522, gson,"database/db.json");
        System.out.println("Server started!");
        server.run();
    }
}
