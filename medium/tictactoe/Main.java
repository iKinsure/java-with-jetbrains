package medium.tictactoe;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int[][] WIN_COMBINATIONS = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    private static final Scanner INPUT = new Scanner(System.in); // input for user handler
    private static final String CLEAR = "         ";
    private static final Player PLAYER_X = new Player(Player.Mode.USER, Player.Side.X); // player side "X"
    private static final Player PLAYER_O = new Player(Player.Mode.USER, Player.Side.O); // player side "O"
    private static int round = 0; // game round counter
    private static String board = CLEAR; // main game board
    private static State gameState = null; // main game state

    /** the main game loop */
    public static void main(String[] args) {
        do {
            enterCommand();
            while (gameState == State.PLAYING) {
                showBoard();
                makeMove();
                gameState = Utilities.getActualGameState(board, WIN_COMBINATIONS);
            }
            clearBoard();
            System.out.println(gameState.getDescription());
        } while (gameState != State.EXIT);
    }

    /** printing board */
    private static void showBoard() {
        System.out.print(Utilities.convertBoard(board));
    }

    /** cleaning aftermath */
    private static void clearBoard() {
        if (gameState != State.EXIT) {
            showBoard();
        }
        board = CLEAR;
        round = 0;
    }

    /** enter input, changing gameState */
    private static void enterCommand() {
        boolean done = false;
        while (!done) {
            System.out.print("Input command: ");
            String[] args = INPUT.nextLine().split(" ");
            try {
                State state = State.valueOf(args[0].toUpperCase());
                if (state == State.EXIT) {
                    gameState = state;
                    done = true;
                } else if (state == State.START) {
                    try {
                        PLAYER_X.setMode(Player.Mode.valueOf(args[1].toUpperCase()));
                        PLAYER_O.setMode(Player.Mode.valueOf(args[2].toUpperCase()));
                        gameState = State.PLAYING;
                        done = true;
                    }
                    catch (Exception e) {
                        System.out.println("Bad parameters!");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Bad command!");
            }
        }
    }

    /** return true if success */
    public static boolean tryToSetCoordinate(char sign, int position) {
        if (Utilities.isCoordinateEmpty(board, position)) {
            board = Utilities.setCoordinate(sign, board, position);
            return true;
        }
        return false;
    }

    /** player choose a coordinate and sets there sign */
    private static void makeMove() {
        Player player = (round % 2 == 0) ? PLAYER_X : PLAYER_O;
        switch (player.getMode()) {
            case USER:
                moveUser(player);
                break;
            case EASY:
                moveEasy(player);
                System.out.println("Making move level \"easy\"");
                break;
            case MEDIUM:
                moveMedium(player);
                System.out.println("Making move level \"medium\"");
                break;
            case HARD:
                moveHard(player);
                System.out.println("Making move level \"hard\"");
                break;
        }
        round++;
    }

    /** easy mode move */
    private static void moveEasy(Player player) {
        boolean done = false;
        while (!done) {
            int position = new Random().nextInt(board.length());
            done = tryToSetCoordinate(player.getSide().sign, position);
        }
    }

    /** user mode move */
    private static void moveUser(Player player) {
        boolean done = false;
        while (!done) {
            System.out.print("Enter the coordinates: ");
            try {
                int x = INPUT.nextInt();
                int y = INPUT.nextInt();
                INPUT.nextLine();
                if (x  >= 1 && x  <= 3 && y >= 1 && y <= 3) {
                    int position = Utilities.convertToCharPosition(x, y);
                    done = tryToSetCoordinate(player.getSide().sign, position);
                    if (!done) {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
                else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                INPUT.nextLine();
            }
        }
    }

    /** medium mode move */
    private static void moveMedium(Player player) {
        boolean done = false;
        int thisPos = Utilities.oneMoveToWin(player.getSide().sign, board, WIN_COMBINATIONS);
        int enemyPos = Utilities.oneMoveToWin(player.getOtherSide().sign, board, WIN_COMBINATIONS);
        while (!done) {
            if (thisPos != -1) {
                done = tryToSetCoordinate(player.getSide().sign, thisPos);
            } else if (enemyPos != -1) {
                done = tryToSetCoordinate(player.getSide().sign, enemyPos);
            } else {
                int randomPosition = new Random().nextInt(board.length());
                done = tryToSetCoordinate(player.getSide().sign, randomPosition);
            }
        }
    }

    /** hard mode move */
    private static void moveHard(Player player) {
        int bestScore = Integer.MIN_VALUE;
        int bestPosition = -1;
        for (int i = 0; i < board.length(); i++) {
            if (Utilities.isCoordinateEmpty(board, i)) {
                StringBuilder builder = new StringBuilder(board);
                builder.setCharAt(i, player.getSide().sign);
                int score = minimax(player, builder.toString(), false);
                if (score > bestScore) {
                    bestScore = score;
                    bestPosition = i;
                }
            }
        }
        tryToSetCoordinate(player.getSide().sign, bestPosition);
    }

    /** minimax algorithm implementation */
    private static int minimax(Player player, String board, boolean isMaximizing) {
        switch (Utilities.getActualGameState(board, WIN_COMBINATIONS)) {
            case X_WIN:
                return (player.getSide() == Player.Side.X) ? 10 : -10;
            case O_WIN:
                return (player.getSide() == Player.Side.O) ? 10 : -10;
            case DRAW:
                return 0;
        }
        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < board.length(); i++) {
            if (Utilities.isCoordinateEmpty(board, i)) {
                StringBuilder builder = new StringBuilder(board);
                builder.setCharAt(i, isMaximizing ? player.getSide().sign : player.getOtherSide().sign);
                int score = minimax(player, builder.toString(), !isMaximizing);
                bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
            }
        }
        return bestScore;
    }
}
