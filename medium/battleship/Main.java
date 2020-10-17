package medium.battleship;

import java.util.Scanner;
import static medium.battleship.BoardManager.*;

public class Main {

    public static final int BOARD_SIZE = 10;
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        Board playerBoard = new Board(BOARD_SIZE);
        Board shotBoard = new Board(BOARD_SIZE);

        System.out.println(playerBoard);

        prepareShips(playerBoard);

        System.out.println("\nThe game starts!\n");
        System.out.println(shotBoard);

        System.out.println("Take a shot!\n");


        while (true) {

            int x;
            int y;
            char sign;
            while (true) {

                String shotPos = SCANNER.next();
                x = playerBoard.getX(shotPos);
                y = playerBoard.getY(shotPos);

                if (isCorrectPosition(x, y, playerBoard)) {
                    sign = playerBoard.getPosition(x, y);
                    if (sign == Board.EMPTY) {
                        shotBoard.setPosition(x, y, Board.MISS);
                        shotBoard.setPosition(x, y, Board.MISS);
                    } else if (sign == Board.SHIP) {
                        shotBoard.setPosition(x, y, Board.HIT);
                        shotBoard.setPosition(x, y, Board.HIT);
                    }

                    break;
                } else {
                    System.out.println("\nError! You entered the wrong coordinates! Try again: \n");
                }
            }

            System.out.println("\n" + shotBoard);

            if (isWin(shotBoard)) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            } else {
                if (sign == Board.EMPTY) {
                    System.out.println("You missed! Try again: ");
                } else if (sign == Board.SHIP) {
                    System.out.println("You hit a ship! Try again: ");
                }
            }
        }
    }

    private static void prepareShips(Board board) {
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
