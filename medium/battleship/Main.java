package medium.battleship;

import java.util.Scanner;

public class Main {

    public static final int BOARD_SIZE = 10;
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        Board board = new Board(BOARD_SIZE);
        System.out.println(board);

        for (Ship ship : Ship.values()) {

            System.out.println("Enter the coordinates of " + ship + ":\n");

            while (true) {

                String startPos = SCANNER.next();
                String endPos = SCANNER.next();

                int x1 = board.getX(startPos);
                int y1 = board.getY(startPos);
                int x2 = board.getX(endPos);
                int y2 = board.getY(endPos);

                if (isCoordsCorrect(x1, x2, y1, y2, ship)) {
                    break;
                } else {
                    System.out.println("Error! Wrong length of the Submarine! Try again: ");
                }
            }
            System.out.println(board);
        }

    }

    private static boolean isCoordsCorrect(int x1, int x2, int y1, int y2, Ship ship) {
        return (x1 == x2 && Math.abs(y1 - y2) == ship.size) || (y1 == y2 && Math.abs(x1 - x2) == ship.size);
    }
}
