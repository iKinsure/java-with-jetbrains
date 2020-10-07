package hard.minesweeper;

public class Mine extends Field {
    public Mine(Mode mode) {
        super(mode);
    }

    @Override
    public String toString() {
        if (this.getMode() == Mode.HIDDEN) {
            return ".";
        } else if (this.getMode() == Mode.SHOWED) {
            return "X";
        } else if (this.getMode() == Mode.MARKED) {
            return "*";
        }
        return "e";
    }
}
