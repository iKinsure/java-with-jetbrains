package medium.battleship;

public class Ship {

    private final ShipType type;
    private int hp;

    private final int xMin;
    private final int yMin;

    private final int xMax;
    private final int yMax;

    public Ship(ShipType type, int x1, int x2, int y1, int y2) {
        this.type = type;
        this.xMin = Math.min(x1, x2);
        this.yMin = Math.min(y1, y2);
        this.xMax = Math.max(x1, x2);
        this.yMax = Math.max(y1, y2);
        this.hp = type.size;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ShipType getType() {
        return type;
    }

    public int getXMin() {
        return xMin;
    }

    public int getYMin() {
        return yMin;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMax() {
        return yMax;
    }
}
