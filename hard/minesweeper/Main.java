package hard.minesweeper;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int SIZE = 9;

    private static int xPos = 0;
    private static int yPos = 0;
    private static String decision = null;
    private static Map map = null;
    private static GameState state = null;

    public static void main(String[] args) {

        // prepare
        map = new Map(SIZE);
        state = GameState.RUNNING;
        System.out.print("How many mines do you want on the field? ");
        int mines = SCANNER.nextInt();
        map.placeMines(mines);
        map.placeDefaults();

        // game loop
        while (state == GameState.RUNNING) {
            System.out.print("\n" + map);
            checkInput();
            if ("free".equals(decision)) {
                moveFree();
            } else if ("mine".equals(decision)) {
                moveMine();
            }
        }

        // end
        System.out.print("\n" + map);
        System.out.println(state.text);
    }

    private static void moveFree() {
        map.markAsFree(xPos, yPos);
        if (map.checkLose(xPos, yPos)) {
            state = GameState.LOST;
            map.setMinesMode(Mode.SHOWED);
        } else if (map.isValueZero(xPos, yPos)) {
            map.discover(xPos, yPos);
        }
        if (map.checkWin()) {
            state = GameState.WON;
        }
    }

    private static void moveMine() {
        map.markAsMine(xPos, yPos);
        if (map.checkWin()) {
            state = GameState.WON;
        }
    }

    private static void getInput() {
        System.out.print("Set/unset mines marks or claim a cell as free: ");
        yPos = SCANNER.nextInt() - 1;
        xPos = SCANNER.nextInt() - 1;
        decision = SCANNER.next();
    }

    private static void checkInput() {
        while (true) {
            getInput();
            if (!map.isPosMode(xPos,yPos, Mode.SHOWED)) {
                break;
            }
            System.out.println("There is a number here!");
        }
    }
}
