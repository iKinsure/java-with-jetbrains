package medium.battleship;

public class BoardManager {

    public static boolean isWin(Board board) {
        return board.getSignCounter(Board.HIT) == ShipType.getAllSize();
    }

    public static boolean isCorrectPosition(int x, int y, Board board) {
        return x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize();
    }

    public static boolean isCorrectShipSize(int x1, int x2, int y1, int y2, ShipType type) {
        boolean isHorizontal = y1 == y2 && Math.abs(x1 - x2) == type.size - 1;
        boolean isVertical = x1 == x2 && Math.abs(y1 - y2) == type.size - 1;
        return isHorizontal || isVertical;
    }

    public static void setShip(Ship ship, Board board) {
        for (int x = ship.getXMin(); x <= ship.getXMax(); x++) {
            for (int y = ship.getYMin(); y <= ship.getYMax(); y++) {
                board.setPosition(x, y, Board.SHIP);
            }
        }
    }

    public static boolean isNoNeighbour(int x1, int x2, int y1, int y2, Board board, char sign) {
        for (int x = Math.min(x1, x2) - 1; x <= Math.max(x1, x2) + 1; x++) {
            for (int y = Math.min(y1, y2) - 1; y <= Math.max(y1, y2) + 1; y++) {
                if (isCorrectPosition(x, y, board)) {
                    if (board.getPosition(x, y) == sign) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
