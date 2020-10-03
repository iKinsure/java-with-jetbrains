package hard.minesweeper;

import java.util.Scanner;

public class Main {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("How many mines do you want on the field? ");
        Map map = new Map(9);
        map.placeMines(SCANNER.nextInt());
        System.out.println(map);
    }
}
