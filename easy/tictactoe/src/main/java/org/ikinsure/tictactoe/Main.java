package org.ikinsure.tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    enum State {
        PLAYING("Game not finished"),
        DRAW("Draw"),
        X("X wins"),
        O("O wins"),
        ERROR("Impossible");
        String text;
        State(String text) {
            this.text = text;
        }
    }

    final static int[][] win = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    static String input = "_________";
    final static char X_SIDE = 'X';
    final static char O_SIDE = 'O';
    final static int X_CORD = 0;
    final static int Y_CORD = 1;
    final static Scanner scan = new Scanner(System.in);
    static int roundCounter = 0;

    private static boolean isWin(char sign) {
        for (int i = 0; i < win.length; i++) {
            boolean wins = true;
            for (int j = 0; j < win[i].length; j++) {
                if (input.charAt(win[i][j]) != sign) {
                    wins = false;
                    break;
                }
            }
            if (wins) {
                return true;
            }
        }
        return false;
    }

    private static int getMoves(char sign) {
        int counter = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == sign) {
                counter++;
            }
        }
        return counter;
    }

    private static boolean isPlayed() {
        return input.contains("_");
    }

    private static State check() {
        boolean isWinX = isWin(X_SIDE);
        boolean isWinO = isWin(O_SIDE);
        if ((isWinX && isWinO) || (Math.abs(getMoves(X_SIDE) - getMoves(O_SIDE)) > 1)) {
            return State.ERROR;
        }
        else if (isWinX) {
            return State.X;
        }
        else if (isWinO) {
            return State.O;
        }
        else if (isPlayed()) {
            return State.PLAYING;
        }
        return State.DRAW;
    }

    public static void main(String[] args) {
        State state = State.PLAYING;
        while (state == State.PLAYING) {
            show();
            boolean error = true;
            while (error) {
                int[] coords = inputCoords();
                error = !setCoordinates(coords[X_CORD], coords[Y_CORD], (roundCounter % 2 == 0) ? X_SIDE : O_SIDE);
                if (error) {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
            state = check();
            roundCounter++;
        }
        show();
        System.out.println(state.text);
    }

    // returns input coords
    private static int[] inputCoords() {
        boolean done = false;
        int[] coords = new int[2];
        while (!done) {
            System.out.print("Enter the coordinates: ");
            try {
                coords[X_CORD] = scan.nextInt();
                coords[Y_CORD] = scan.nextInt();
                if (coords[X_CORD]  >= 1 && coords[X_CORD]  <= 3 && coords[Y_CORD]  >= 1 && coords[Y_CORD]  <= 3) {
                    done = true;
                }
                else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scan.nextLine();
            }
        }
        return coords;
    }

    private static boolean setCoordinates(int x, int y, char sign) {
        StringBuilder builder = new StringBuilder(input);
        int position = --x + 3*(--y);
        if (y == 2) {
            position -= 6;
        }
        if (y == 0) {
            position += 6;
        }
        if (builder.charAt(position) == '_') {
            builder.setCharAt(position, sign);
            input = builder.toString();
            return true;
        }
        return false;
    }

    private static void show() {
        System.out.println("---------\n" +
                "| " + input.charAt(0) + " " + input.charAt(1) + " " + input.charAt(2) + " |\n" +
                "| " + input.charAt(3) + " " + input.charAt(4) + " " + input.charAt(5) + " |\n" +
                "| " + input.charAt(6) + " " + input.charAt(7) + " " + input.charAt(8) + " |\n" +
                "---------");
    }
}
