package org.ikinsure.hard.banking;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // get database name from args and start
        new Client(args[Arrays.asList(args).indexOf("-fileName") + 1]);
    }
}
