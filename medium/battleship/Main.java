package medium.battleship;

import java.util.Scanner;
import static medium.battleship.BoardManager.*;

public class Main {

    public static final int BOARD_SIZE = 10;
    public static final int PLAYERS = 2;
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        Board[] boards = new Board[PLAYERS];
        for (int i = 0; i < PLAYERS; i++) {
            boards[i] = new Board(BOARD_SIZE);
        }

        System.out.println(boards[0]);

        prepare(boards[0]);

        System.out.println("\nThe game starts!\n");
        System.out.println(boards[1]);

        System.out.println("Take a shot!\n");

        int x;
        int y;
        char sign;
        while (true) {

            String shot = SCANNER.next();
            x = boards[0].getX(shot);
            y = boards[0].getY(shot);

            if (isCorrectPosition(x, y, boards[0])) {
                sign = boards[0].getPosition(x, y);
                if (sign == Board.EMPTY) {
                    boards[1].setPosition(x, y, Board.MISS);
                    boards[0].setPosition(x, y, Board.MISS);
                } else if (sign == Board.SHIP) {
                    boards[1].setPosition(x, y, Board.HIT);
                    boards[0].setPosition(x, y, Board.HIT);
                }

                break;
            } else {
                System.out.println("\nError! You entered the wrong coordinates! Try again: \n");
            }
        }
        if (sign == Board.EMPTY) {
            System.out.println("You missed!");
        } else if (sign == Board.SHIP) {
            System.out.println("You hit a ship!");
        }

        System.out.println("\n" + boards[0]);
    }

    private static void prepare(Board board) {
        for (Ship ship : Ship.values()) {
            System.out.println("Enter the coordinates of " + ship + ":\n");
            while (true) {

                String startPos = SCANNER.next();
                String endPos = SCANNER.next();

                final int X1 = board.getX(startPos);
                final int Y1 = board.getY(startPos);
                final int X2 = board.getX(endPos);
                final int Y2 = board.getY(endPos);

                if (isCorrectShipSize(X1, X2, Y1, Y2, ship)) {
                    if (isCorrectShipNeighbour(X1, X2, Y1, Y2, board)) {
                        setShip(X1, X2, Y1, Y2, board);
                        break;
                    } else {
                        System.out.println("\nError! You placed it too close to another one. Try again: ");
                    }
                } else {
                    System.out.println("\nError! Wrong length of the Submarine! Try again: ");
                }
            }
            System.out.println("\n" + board);
        }
    }
}
