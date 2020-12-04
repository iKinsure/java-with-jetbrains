package com.ikinsure.hyperskill.medium.battleship;

import java.util.Scanner;
import static com.ikinsure.hyperskill.medium.battleship.BoardManager.*;

public class Main {

    public static final int BOARD_SIZE = 10;
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        Player p1 = new Player(BOARD_SIZE, "Player 1");
        prepare(p1);

        Player p2 = new Player(BOARD_SIZE, "Player 2");
        prepare(p2);

        boolean round = true;
        boolean isWin = false;
        while (!isWin) {
            isWin = round ? turn(p1, p2) : turn(p2, p1);
            if (!isWin) {
                round = !round;
                pass();
            }
        }
    }

    private static void pass() {
        System.out.println("Press Enter and pass the move to another player");
        SCANNER.nextLine();
        SCANNER.nextLine();
    }

    // ================= turn methods =====================================================

    private static boolean turn(Player player, Player enemy) {
        System.out.println(player);
        System.out.println(player.getName() + ", it's your turn: \n");
        return makeShot(player, enemy);
    }

    private static boolean makeShot(Player player, Player enemy) {
        char sign;
        Ship ship = null;
        while (true) {

            String shotPos = SCANNER.next().toUpperCase();
            int x = enemy.getShipBoard().getX(shotPos);
            int y = enemy.getShipBoard().getY(shotPos);

            if (isCorrectPosition(x, y, enemy.getShipBoard())) {
                sign = enemy.getShipBoard().getPosition(x, y);
                if (sign == Board.EMPTY) {
                    player.getShotBoard().setPosition(x, y, Board.MISS);
                    enemy.getShipBoard().setPosition(x, y, Board.MISS);
                } else if (sign == Board.SHIP) {
                    ship = enemy.getShipByPosition(x, y);
                    ship.setHp(ship.getHp() - 1);
                    player.getShotBoard().setPosition(x, y, Board.HIT);
                    enemy.getShipBoard().setPosition(x, y, Board.HIT);
                }
                break;
            } else {
                System.out.println("\nError! You entered the wrong coordinates! Try again: \n");
            }
        }
        if (isWin(player.getShotBoard())) {
            System.out.println("\nYou sank the last ship. You won. Congratulations " + player.getName() + "!\n");
            return true;
        } else {
            if (sign == Board.EMPTY) {
                System.out.println("\nYou missed!\n");
            } else if (sign == Board.SHIP) {
                if (ship.getHp() == 0) {
                    System.out.println("\nYou sank a ship!\n");
                } else {
                    System.out.println("\nYou hit a ship!\n");
                }
            }
        }
        return false;
    }


    // ================= prepare methods =====================================================

    private static void prepare(Player player) {
        System.out.println(player.getName() + ", place your ships on the game field\n");
        System.out.println(player.getShipBoard());
        prepareShips(player);
        pass();
    }

    private static void prepareShips(Player player) {
        for (ShipType type : ShipType.values()) {
            System.out.println("Enter the coordinates of " + type + ":\n");
            boolean isSuccess = false;
            while (!isSuccess) {
                isSuccess = tryPrepareShip(player, type);
            }
            System.out.println("\n" + player.getShipBoard());
        }
    }

    private static boolean tryPrepareShip(Player player, ShipType type) {
        String startPos = SCANNER.next().toUpperCase();
        String endPos = SCANNER.next().toUpperCase();

        final int X1;
        final int Y1;
        final int X2;
        final int Y2;

        try {

            X1 = player.getShipBoard().getX(startPos);
            Y1 = player.getShipBoard().getY(startPos);
            X2 = player.getShipBoard().getX(endPos);
            Y2 = player.getShipBoard().getY(endPos);

            if (isCorrectShipSize(X1, X2, Y1, Y2, type)) {
                if (isNoNeighbour(X1, X2, Y1, Y2, player.getShipBoard(), Board.SHIP)) {
                    Ship ship = new Ship(type, X1, X2, Y1, Y2);
                    player.addShip(ship);
                    setShip(ship, player.getShipBoard());
                    return true;
                } else {
                    System.out.println("\nError! You placed it too close to another one. Try again: ");
                }
            } else {
                System.out.println("\nError! Wrong length of the Submarine! Try again: ");
            }

        } catch (Exception e) {
            System.out.println("\nError! Invalid input format! Try again: ");
            return false;
        }
        return false;
    }
}
