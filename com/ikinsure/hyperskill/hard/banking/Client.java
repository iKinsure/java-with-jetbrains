package com.ikinsure.hyperskill.hard.banking;

import com.ikinsure.hyperskill.hard.banking.model.Bank;
import com.ikinsure.hyperskill.hard.banking.model.Card;
import com.ikinsure.hyperskill.hard.banking.view.Menu;
import com.ikinsure.hyperskill.hard.banking.view.MenuController;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Client {

    private final Scanner scanner;
    private final MenuController view;
    private final Bank bank;
    private final SQLiteDataSource dataSource;
    private Card activeCard;

    public Client(DBController database) {

        this.scanner = new Scanner(System.in);
        this.view = new MenuController();
        this.bank = database.getBank();
        this.dataSource = database.getDataSource();


        view.run(new Menu.Builder()
                .setScanner(scanner)
                .addItem(1, "Create an account", this::createAccount)
                .addItem(2, "Log into account", this::logToAccount)
                .addItem(0, "Exit", view::exitMenu)
                .build());
    }

    private void logOut() {
        view.exitMenu();
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

            view.setMenu(new Menu.Builder()
                    .setScanner(scanner)
                    .addItem(1, "Balance", this::printBalance)
                    .addItem(2, "Log out", this::logOut)
                    .addItem(0, "Exit", view::exitAll)
                    .build());

        } else {
            System.out.println("Wrong card number or PIN!\n");
        }
    }
}
