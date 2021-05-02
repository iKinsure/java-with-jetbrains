package org.ikinsure.tictactoe;

import java.util.Scanner;

public class Main {

    public static final String SEPARATOR = "|";
    public static final String DELIMITER = " ";
    public static final String BORDER = "-";
    public static final int BOARD_SIZE = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(scanner, BOARD_SIZE);
        game.run();
    }

}
