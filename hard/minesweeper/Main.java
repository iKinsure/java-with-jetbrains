package hard.minesweeper;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int SIZE = 9;

    public static void main(String[] args) {

        // prepare
        Map map = new Map(SIZE);
        System.out.print("How many mines do you want on the field? ");
        map.placeMines(SCANNER.nextInt());
        map.placeMinesCounters();

        // game loop
        boolean isWin = false;
        while (!isWin) {
            System.out.print("\n" + map);
            boolean isSuccess = false;
            while (!isSuccess) {
                System.out.print("Set/delete mines marks (x and y coordinates): ");
                final int Y = SCANNER.nextInt() - 1;
                final int X = SCANNER.nextInt() - 1;
                isSuccess = map.mark(X, Y);
                if (!isSuccess) {
                    System.out.println("There is a number here!");
                }
                isWin = map.checkWin();
            }
        }

        // end
        System.out.print("\n" + map);
        System.out.println("Congratulations! You found all mines!");
    }
}
