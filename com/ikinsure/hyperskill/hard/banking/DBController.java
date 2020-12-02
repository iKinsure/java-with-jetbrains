package com.ikinsure.hyperskill.hard.banking;

import org.sqlite.SQLiteDataSource;

public class DBController {

    private static DBController instance;
    public final SQLiteDataSource dataSource;
    public final Bank bank;

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
}
