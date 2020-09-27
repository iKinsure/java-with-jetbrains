package hard.solver;

/**
 * Class represents row vector
 */

public class Row extends Vector implements Comparable<Row> {

    private final int priority;

    public Row(double[] elements) {
        super(elements);
        this.priority = calculatePriority();
    }

    private int calculatePriority() {
        int nullCounter = 0;
        while (nullCounter < getSize() &&  Utility.equals(getElement(nullCounter), 0.0)) {
            nullCounter++;
        }
        return nullCounter;
    }

    /**
     * priority is based on number of zeros at beginning
     * @return priority
     */
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Row o) {
        if (this.priority > o.getPriority()) {
            return 1;
        }
        else if (this.priority == o.getPriority()) {
            return 0;
        }
        return -1;
    }
}
