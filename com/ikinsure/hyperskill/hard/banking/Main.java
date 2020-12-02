package com.ikinsure.hyperskill.hard.banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Bank bank = new Bank();
    private static final SQLiteDataSource DATA_SOURCE = new SQLiteDataSource();

    public static void main(String[] args) {
        createDataBase(args);

        try (Connection connection =  DATA_SOURCE.getConnection()) {
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS card (" +
                        "id INTEGER, " +
                        "number TEXT, " +
                        "pin TEXT, " +
                        "balance INTEGER DEFAULT 0);");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            switch (SCANNER.nextInt()) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    if (!logToAccount()) {
                        return;
                    }
                    break;
                default:
                    System.out.println("Bye!");
                    return;
            }
        }
    }

    private static void insert(Card card) {
        String sql = "INSERT INTO card(id, number, pin, balance) VALUES(?,?,?,?)";
        try (Connection connection = DATA_SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bank.getCardsCounter());
            statement.setString(2, card.getNumber());
            statement.setString(3, card.getPin());
            statement.setInt(4, card.getBalance());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDataBase(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-fileName")) {
                String file = "jdbc:sqlite:" + args[i + 1];
                DATA_SOURCE.setUrl(file);
            }
        }
    }

    private static void createAccount() {
        Card card = bank.createCard();
        System.out.println("Your card has been created\n" +
                "Your card number:\n" +
                card.getNumber() + "\n" +
                "Your card PIN:\n" +
                card.getPin());
        insert(card);
    }

    private static boolean logToAccount() {
        System.out.println("Enter your card number:");
        String number = SCANNER.next();
        System.out.println("Enter your PIN:");
        String pin = SCANNER.next();
        Optional<Card> card = bank.logToCard(number, pin);
        if (card.isPresent()) {
            System.out.println("You have successfully logged in");
            while (true) {
                System.out.println("1. Balance\n" +
                        "2. Log out\n" +
                        "0. Exit");
                if (SCANNER.nextInt() == 1) {
                    System.out.println("Balance: " + card.get().getBalance());
                } else {
                    System.out.println("You have successfully logged out!");
                    return false;
                }
            }
        } else {
            System.out.println("Wrong card number or PIN!");
        }
        return true;
    }
}
