package hard.solver;

public class Matrix {

    private final double[][] array;

    public Matrix(double[][] array) {
        if (isMatrix(array)) {
            this.array = array;
        } else {
            throw new IllegalArgumentException("Array is not matrix!");
        }
    }

    private boolean isMatrix(double[][] array) {
        for (double[] a : array) {
            if (a.length != array[0].length) {
                return false;
            }
        }
        return true;
    }

    public void multiplyRow(int rowIndex,double value) {
        for (int x = 0; x < array[0].length; x++) {
            array[rowIndex][x] *= value;
        }
    }

    public void swapRows(int firstRowIndex, int secondRowIndex) {
        for (int x = 0; x < array[0].length; x++) {
            double temporary = array[firstRowIndex][x];
            array[firstRowIndex][x] = array[secondRowIndex][x];
            array[secondRowIndex][x] = temporary;
        }
    }

    public void swapCols(int firstColIndex, int secondColIndex) {
        for (int y = 0; y < array.length; y++) {
            double temporary = array[y][firstColIndex];
            array[y][firstColIndex] = array[y][secondColIndex];
            array[y][secondColIndex] = temporary;
        }
    }

    public void subtractRows(int toIndex, int fromIndex) {
        for (int x = 0; x < array[0].length; x++) {
            array[toIndex][x] -= array[fromIndex][x];
        }
    }

    public boolean sort() {
        int size = Math.min(array.length, array[0].length - 1);
        for (int i = 0; i < size; i++) {
            if (Util.equals(array[i][i], 0.0)) {
                if (!sortRows(i)) {
                    return false;
                }
            }
        }
        return true; // success
    }

    private boolean sortRows(int i) {
        for (int y = array.length - 1; y >= i + 1; y--) {
            if (!Util.equals(array[y][i], 0.0)) {
                swapRows(i, y);
                return true;
            }
        }
        return sortColumns(i);
    }

    private boolean sortColumns(int i) {
        for (int x = i + 1; x < array[0].length - 1; x++) {
            if (!Util.equals(array[i][x], 0.0)) {
                swapCols(i, x);
                return true;
            }
        }
        return sortRest(i);
    }

    private boolean sortRest(int i) {
        for (int y = i + 1; y < array.length; y++) {
            for (int x = i + 1; x < array[0].length - 1; x++) {
                if (!Util.equals(array[y][x], 0.0)) {
                    swapRows(i, y);
                    swapCols(i, x);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkNoSolution() {
        for (double[] a : array) {
            int counter = 0;
            for (int x = 0; x < array[0].length - 1; x++) {
                if (Util.equals(a[x], 0.0)) {
                    counter++;
                }
            }
            if (counter == array[0].length - 1) {
                if (!Util.equals(a[array[0].length - 1], 0.0)) {
                    return true; // no solutions
                }
            }
        }
        return false;
    }

    public int zeroRowsCounter() {
        int resultCounter = 0;
        for (double[] a : array) {
            boolean isNull = true;
            for (int x = 0; x < array[0].length; x++) {
                if (!Util.equals(a[x], 0.0)) {
                    isNull = false;
                    break;
                }
            }
            if (isNull) {
                resultCounter++;
            }
        }
        return resultCounter;
    }

    public void echelonForm() {
        int size = Math.min(array.length, array[0].length - 1);
        for (int i = 0; i < size; i++) {
            if (!Util.equals(array[i][i], 0.0)) {
                multiplyRow(i, 1.0 / array[i][i]);
                for (int y = i + 1; y < array.length; y++) {
                    if (!Util.equals(array[y][i], 0.0)) {
                        multiplyRow(y, 1.0 / array[y][i]);
                        subtractRows(y, i);
                    }
                }
            }
        }
    }

    public void reducedRowEchelonForm() {
        int size = Math.min(array.length, array[0].length - 1);
        for (int i = 1; i < size; i++) {
            for (int y = i - 1; y >= 0; y--) {
                if (!Util.equals(array[i][i], 0.0)) {
                    multiplyRow(i, array[y][i]);
                    subtractRows(y, i);
                    multiplyRow(i, 1.0 / array[i][i]);
                }
            }
        }
    }

    public double[] getNewColMultiplied(int colIndex, double value) {
        double[] res = new double[array.length];
        for (int y = 0; y < array.length; y++) {
            res[y] = array[y][colIndex] * value;
        }
        return res;
    }

    public int getRowsCounter() {
        return array.length;
    }

    public int getColsCounter() {
        return array[0].length;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (double[] a : array) {
            for (int x = 0; x < array[0].length; x++) {
                res.append(a[x]).append(' ');
            }
            res.append('\n');
        }
        return res.toString();
    }
}
