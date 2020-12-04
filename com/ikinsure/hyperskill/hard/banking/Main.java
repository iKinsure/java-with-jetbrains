package com.ikinsure.hyperskill.hard.banking;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // get database name from args
        String file = args[Arrays.asList(args).indexOf("-fileName") + 1];

        // create database controller and set url
        DBController controller = DBController.getInstance();
        controller.getDatabase().createTable(file);

        // start app
        new Client(controller);
    }
}
