package com.ikinsure.hyperskill.hard.banking;

import com.ikinsure.hyperskill.hard.banking.model.Bank;

public class DBController {

    private static DBController instance;
    private final Bank bank;
    private final Database database;

    private DBController() {
        this.bank = new Bank();
        this.database = new Database();
    }

    public static DBController getInstance() {
        if (instance == null) {
            instance = new DBController();
        }
        return instance;
    }

    public Bank getBank() {
        return bank;
    }

    public Database getDatabase() {
        return database;
    }
}
