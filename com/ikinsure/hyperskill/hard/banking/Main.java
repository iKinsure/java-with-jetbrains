package com.ikinsure.hyperskill.hard.banking;

import com.ikinsure.hyperskill.hard.banking.controller.UserGUI;
import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        DBController db = DBController.getInstance();
        createDataBase(args, db.dataSource);
        UserGUI client = new UserGUI(SCANNER, db.bank, db.dataSource);
        client.run();
    }

    private static void createDataBase(String[] args, SQLiteDataSource dataSource) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-fileName")) {
                String file = "jdbc:sqlite:" + args[i + 1];
                dataSource.setUrl(file);
                try (Connection connection =  dataSource.getConnection();
                     Statement statement = connection.createStatement()) {
                        statement.execute("CREATE TABLE IF NOT EXISTS card (" +
                                "id INTEGER, number TEXT, pin TEXT, balance INTEGER DEFAULT 0);");
                }
                break;
            }
        }
    }
}
