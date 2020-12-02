package com.ikinsure.hyperskill.hard.banking;

import com.ikinsure.hyperskill.hard.banking.model.Bank;
import org.sqlite.SQLiteDataSource;

public class DBController {

    private static DBController instance;
    private final SQLiteDataSource dataSource;
    private final Bank bank;

    private DBController() {
        this.dataSource = new SQLiteDataSource();
        this.bank = new Bank();
    }

    public static DBController getInstance() {
        if (instance == null) {
            instance = new DBController();
        }
        return instance;
    }

    public SQLiteDataSource getDataSource() {
        return dataSource;
    }

    public Bank getBank() {
        return bank;
    }
}
