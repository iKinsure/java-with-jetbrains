package medium.battleship;

import java.util.Scanner;

public class Main {

    public static final int BOARD_SIZE = 10;
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        Board board = new Board(BOARD_SIZE);
        System.out.println(board);

        prepare(board);

        System.out.println("\nThe game starts!\n");
        System.out.println(board);

        System.out.println("Take a shot!\n");

        int x;
        int y;
        while (true) {

            String shot = SCANNER.next();
            x = board.getX(shot);
            y = board.getY(shot);

            if (isPositionCorrect(x, y)) {
                break;
            } else {
                System.out.println("\nError! You entered the wrong coordinates! Try again: \n");
            }
        }
        char sign = board.getPosition(x, y);
        if (sign == Board.FOG_OF_WAR) {
            System.out.println("You missed!");
            board.setPosition(x, y, Board.MISS);
        } else if (sign == Board.OUR_SHIP) {
            System.out.println("You hit a ship!");
            board.setPosition(x, y, Board.HIT);
        }

        System.out.println("\n" + board);
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

                if (isCorrectSize(X1, X2, Y1, Y2, ship)) {
                    if (isTooClose(X1, X2, Y1, Y2, ship, board)) {
                        System.out.println("\nError! You placed it too close to another one. Try again: ");
                    } else {
                        setShips(X1, X2, Y1, Y2, ship, board);
                        break;
                    }
                } else {
                    System.out.println("\nError! Wrong length of the Submarine! Try again: ");
                }
            }
            System.out.println("\n" + board);
        }
    }

    private static boolean isPositionCorrect(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    private static void setShips(int x1, int x2, int y1, int y2, Ship ship, Board board) {
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                board.setPosition(x, y, Board.OUR_SHIP);
            }
        }
    }

    private static boolean isTooClose(int x1, int x2, int y1, int y2, Ship ship, Board board) {
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (Direction dir : Direction.values()) {
                    final int X = x + dir.x;
                    final int Y = y + dir.y;
                    if (isPositionCorrect(X, Y)) {
                        if (board.getPosition(X, Y) == Board.OUR_SHIP) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isCorrectSize(int x1, int x2, int y1, int y2, Ship ship) {
        boolean isHorizontal = y1 == y2 && Math.abs(x1 - x2) == ship.size - 1;
        boolean isVertical = x1 == x2 && Math.abs(y1 - y2) == ship.size - 1;
        return isHorizontal || isVertical;
    }
}
