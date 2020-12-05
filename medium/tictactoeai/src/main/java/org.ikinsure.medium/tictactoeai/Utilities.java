package org.ikinsure.medium.tictactoeai;

/**
 * Class with tools for Tic-Tac-Toe game
 */
public class Utilities {

    /**
     * @param board in a single line
     * @return board to be shown
     */
    public static String convertBoard(String board) {
        return "---------\n" +
                String.format("| %c %c %c |\n", board.charAt(0), board.charAt(1), board.charAt(2)) +
                String.format("| %c %c %c |\n", board.charAt(3), board.charAt(4), board.charAt(5)) +
                String.format("| %c %c %c |\n", board.charAt(6), board.charAt(7), board.charAt(8)) +
                "---------\n";
    }

    /**
     *
     * @param x coordinate
     * @param y coordinate
     * @return position to be used in board String
     */
    public static int convertToCharPosition(int x, int y) {
        return 6 + (x - 1) - 3 * (y - 1);
    }

    /**
     * @param sign player side
     * @param board of game
     * @param wins array with winning combinations
     * @return if there is winner
     */
    public static boolean checkWinner(char sign, String board, int[][] wins) {
        for (int[] win : wins) {
            boolean thereIsWinner = true;
            for (int i : win) {
                if (board.charAt(i) != sign) {
                    thereIsWinner = false;
                    break;
                }
            }
            if (thereIsWinner) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param board of game
     * @return if there is no space in board
     */
    public static boolean isBoardBlocked(String board) {
        return !board.contains(" ");
    }

    /**
     * @param board of game
     * @param position in board
     * @return if board at position is empty
     */
    public static boolean isCoordinateEmpty(String board, int position) {
        if (position < 0) {
            return false;
        }
        return board.charAt(position) == ' ';
    }

    /**
     * @param sign to be set
     * @param board of game
     * @param position in board to be set
     * @return a board with sign at position
     */
    public static String setCoordinate(char sign, String board, int position) {
        StringBuilder builder = new StringBuilder(board);
        builder.setCharAt(position, sign);
        return builder.toString();
    }

    /**
     * @param sign player side
     * @param board of game
     * @param wins array with winning combinations
     * @return first position which gives win or -1 if not found
     */
    public static int oneMoveToWin(char sign, String board, int[][] wins) {
        for (int[] win : wins) {
            int position = -1;
            int counter = 0;
            for (int i : win) {
                if (board.charAt(i) == sign) {
                    counter++;
                } else if (board.charAt(i) == ' ') {
                    position = i;
                }
            }
            if (counter >= 2 && position != -1) {
                return position;
            }
        }
        return -1;
    }

    /**
     *
     * @param board of game
     * @param wins array with winning combinations
     * @return returns refreshed game state
     */
    public static State getActualGameState(String board, int[][] wins) {
        if (checkWinner(Player.Side.X.sign, board, wins)) {
            return State.X_WIN;
        } else if (checkWinner(Player.Side.O.sign, board, wins)) {
            return State.O_WIN;
        } else if (isBoardBlocked(board)) {
            return State.DRAW;
        } else {
            return State.PLAYING;
        }
    }
}
