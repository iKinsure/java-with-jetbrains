package org.ikinsure.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    private final Board board;
    private final Scanner scanner;

    public Game(Scanner scanner, int size) {
        this.board = new Board(size);
        this.scanner = scanner;
    }

    public void run() {

        State state = State.PLAY;
        Field field = Field.X;

        while (state == State.PLAY) {
            System.out.println(board.getBoard());
            while (!input(field)) {}
            state = board.checkState(field);
            field = field == Field.X ? Field.O : Field.X;
        }

        System.out.println(board.getBoard());
        System.out.println(state.getMsg());
    }

    private boolean input(Field field) {

        int x;
        int y;
        System.out.print("Enter the coordinates: ");

        try {
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.println("You should enter numbers!");
            scanner.nextLine();
            return false;
        }

        if (x < 0 || y < 0 || x >= board.size() || y >= board.size()) {
            System.out.println("Coordinates should be from 1 to " + board.size() + "!");
            return false;
        }

        if (!board.get(x, y).equals(Field.EMPTY)) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        board.set(x, y, field);
        return true;
    }

}
