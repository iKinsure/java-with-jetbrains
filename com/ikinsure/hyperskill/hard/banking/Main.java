package com.ikinsure.hyperskill.hard.banking;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {
        DBController database = DBController.getInstance();
        createDatabase(args, database);
        new Client(database);
    }

    private static void createDatabase(String[] args, DBController db) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-fileName")) {
                String file = "jdbc:sqlite:" + args[i + 1];
                db.getDataSource().setUrl(file);
                try (Connection connection =  db.getDataSource().getConnection();
                     Statement statement = connection.createStatement()) {
                        statement.execute("CREATE TABLE IF NOT EXISTS card (" +
                                "id INTEGER, number TEXT, pin TEXT, balance INTEGER DEFAULT 0);");
                }
                break;
            }
        }
    }
}
