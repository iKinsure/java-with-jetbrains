package medium.battleship;

public class BoardManager {

    public static boolean isCorrectPosition(int x, int y, Board board) {
        return x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize();
    }

    public static boolean isCorrectShipSize(int x1, int x2, int y1, int y2, Ship ship) {
        boolean isHorizontal = y1 == y2 && Math.abs(x1 - x2) == ship.size - 1;
        boolean isVertical = x1 == x2 && Math.abs(y1 - y2) == ship.size - 1;
        return isHorizontal || isVertical;
    }

    public static void setShip(int x1, int x2, int y1, int y2, Board board) {
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                board.setPosition(x, y, Board.SHIP);
            }
        }
    }

    public static boolean isCorrectShipNeighbour(int x1, int x2, int y1, int y2, Board board) {
        for (int x = Math.min(x1, x2) - 1; x <= Math.max(x1, x2) + 1; x++) {
            for (int y = Math.min(y1, y2) - 1; y <= Math.max(y1, y2) + 1; y++) {
                if (isCorrectPosition(x, y, board)) {
                    if (board.getPosition(x, y) == Board.SHIP) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
