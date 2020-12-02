package com.ikinsure.hyperskill.hard.banking.controller;

import com.ikinsure.hyperskill.hard.banking.Bank;
import com.ikinsure.hyperskill.hard.banking.Card;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Scanner;

public class UserGUI {

    private final ArrayDeque<Menu> menuStack;
    private final Scanner scanner;
    private final Bank bank;
    private final SQLiteDataSource dataSource;
    private Card activeCard;

    public UserGUI(Scanner scanner, Bank bank, SQLiteDataSource dataSource) {
        this.menuStack = new ArrayDeque<>();
        this.scanner = scanner;
        this.bank = bank;
        this.dataSource = dataSource;

        menuStack.offerLast(new Menu.Builder()
                .setScanner(scanner)
                .addItem(1, "Create an account", this::createAccount)
                .addItem(2, "Log into account", this::logToAccount)
                .addItem(0, "Exit", menuStack::pollLast)
                .build());
    }

    public void run() {
        while (!menuStack.isEmpty()) {
            menuStack.getLast().selectItem().action.execute();
        }
        System.out.println("Bye!");
    }

    private void logOut() {
        menuStack.pollLast();
        System.out.println("You have successfully logged out!\n");
    }

    private void printBalance() {
        System.out.println("Balance: " + activeCard.getBalance() + "\n");
    }

    private void insert(Card card) {
        String sql = "INSERT INTO card(id, number, pin, balance) VALUES(?,?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
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

    private void createAccount() {
        Card card = bank.createCard();
        System.out.println("Your card has been created\n" +
                "Your card number:\n" +
                card.getNumber() + "\n" +
                "Your card PIN:\n" +
                card.getPin() + "\n");
        insert(card);
    }

    private void logToAccount() {
        System.out.println("Enter your card number:");
        String number = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();
        System.out.println();
        Optional<Card> card = bank.logToCard(number, pin);
        if (card.isPresent()) {
            this.activeCard = card.get();
            System.out.println("You have successfully logged in\n");
            menuStack.offerLast(new Menu.Builder()
                    .setScanner(scanner)
                    .addItem(1, "Balance", this::printBalance)
                    .addItem(2, "Log out", this::logOut)
                    .addItem(0, "Exit", menuStack::clear)
                    .build());
        } else {
            System.out.println("Wrong card number or PIN!\n");
        }
    }
}
