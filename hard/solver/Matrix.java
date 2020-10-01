package hard.solver;

public class Matrix {

    private double[][] array;

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

    public void addRows(int toIndex, int fromIndex) {
        for (int x = 0; x < array[0].length; x++) {
            array[toIndex][x] += array[fromIndex][x];
        }
    }

    public void subtractRows(int toIndex, int fromIndex) {
        for (int x = 0; x < array[0].length; x++) {
            array[toIndex][x] -= array[fromIndex][x];
        }
    }

    public void subtractRows(int toIndex, double[] row) {
        for (int x = 0; x < array[0].length; x++) {
            array[toIndex][x] -= row[x];
        }
    }

    public void prepare() {
        for (int i = 0; i < Math.min(array.length, array[0].length); i++) {
            if (Util.equals(array[i][i], 0.0)) {
                boolean isAllZero = true;
                for (int y = i + 1; y < array.length; y++) {
                    if (!Util.equals(array[y][i], 0.0)) {
                        swapRows(i, y);
                        isAllZero = false;
                        break;
                    }
                }
                if (isAllZero) {
                    prepareNext(i);
                }
            }
        }
    }

    private void prepareNext(int i) {
        boolean isAllZero = true;
        for (int x = i + 1; x < array[0].length; x++) {
            if (!Util.equals(array[i][x], 0.0)) {
                swapCols(i, x);
                isAllZero = false;
                break;
            }
        }
        if (isAllZero) {
            prepareNextNext(i);
        }
    }

    private void prepareNextNext(int i) {
        boolean isAllZero = true;
        for (int y = i + 1; y < array.length; y++) {
            for (int x = i + 1; x < array[0].length; x++) {
                if (!Util.equals(array[y][x], 0.0)) {
                    swapRows(i, y);
                    swapCols(i, x);
                    isAllZero = false;
                    break;
                }
            }
            if (isAllZero) {
                System.out.println("Same zera");
            }
        }
    }

    public void check() {

    }

    public void echelonForm() {
        for (int i = 0; i < Math.min(array.length, array[0].length); i++) {
            multiplyRow(i, 1.0 / array[i][i]);
            for (int y = i + 1; y < array.length; y++) {
                multiplyRow(y, 1.0 / array[y][i]);
                subtractRows(y, i);
            }
        }
    }

    public void reducedRowEchelonForm() {
        for (int i = 1; i < Math.min(array.length, array[0].length); i++) {
            for (int y = i - 1; y >= 0; y--) {
                multiplyRow(i, array[y][i]);
                subtractRows(y, i);
                multiplyRow(i, 1.0 / array[i][i]);
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

    public double[] getNewRowMultiplied(int rowIndex, double value) {
        double[] res = new double[array[0].length];
        for (int x = 0; x < array[0].length; x++) {
            res[x] = array[rowIndex][x] * value;
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
