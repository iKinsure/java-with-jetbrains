package org.ikinsure.minesweeper;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int SIZE = 9;
    private static int xPos = 0;
    private static int yPos = 0;
    private static String decision = null;
    private static final Map map = new Map(SIZE);
    private static GameState state = GameState.RUNNING;

    public static void main(String[] args) {
        prepare();
        while (state == GameState.RUNNING) {
            System.out.print("\n" + map);
            checkInput();
            if ("free".equals(decision)) {
                moveFree();
            } else if ("mine".equals(decision)) {
                moveMine();
            }
        }
        System.out.print("\n" + map);
        System.out.println(state.text);
    }

    private static void moveFree() {
        map.markAsFree(xPos, yPos);
        if (map.checkLose(xPos, yPos)) {
            state = GameState.LOST;
            map.setMinesMode();
        } else if (map.isValueZero(xPos, yPos)) {
            map.discover(xPos, yPos);
        }
        checkWin();
    }

    private static void prepare() {
        System.out.print("How many mines do you want on the field? ");
        int mines = SCANNER.nextInt();
        System.out.print("\n" + map);
        getInput();
        map.placeMines(mines, xPos, yPos);
        map.placeDefaults();
        moveFree();
    }

    private static void moveMine() {
        map.markAsMine(xPos, yPos);
        checkWin();
    }

    private static void checkWin() {
        if (map.checkWinFirst() || map.checkWinSecond()) {
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
