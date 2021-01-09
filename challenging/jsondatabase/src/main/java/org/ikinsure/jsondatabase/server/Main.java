package org.ikinsure.jsondatabase.server;

/**
 * Entry point
 */
public class Main {

    public static void main(String[] args) {
        Database db = new Database();
        Server server = new Server(34522, db);
        System.out.println("Server started!");
        server.run();
    }
}
