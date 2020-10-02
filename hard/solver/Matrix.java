package hard.solver;

public class Matrix {

    private Complex[][] array;

    public Matrix(Complex[][] array) {
        if (isMatrix(array)) {
            this.array = array;
        } else {
            throw new IllegalArgumentException("Array is not matrix!");
        }
    }

    private boolean isMatrix(Complex[][] array) {
        for (Complex[] c : array) {
            if (c.length != array[0].length) {
                return false;
            }
        }
        return true;
    }

    public void multiplyRow(int rowIndex, double value) {
        for (int x = 0; x < array[0].length; x++) {
            array[rowIndex][x].multiply(value);
        }
    }

    public void multiplyRow(int rowIndex, Complex value) {
        for (int x = 0; x < array[0].length; x++) {
            array[rowIndex][x].multiply(value);
        }
    }

    public void divideRow(int rowIndex, double value) {
        for (int x = 0; x < array[0].length; x++) {
            array[rowIndex][x].divide(value);
        }
    }

    public void divideRow(int rowIndex, Complex value) {
        for (int x = 0; x < array[0].length; x++) {
            array[rowIndex][x].divide(value);
        }
    }

    public void swapRows(int firstRowIndex, int secondRowIndex) {
        for (int x = 0; x < array[0].length; x++) {
            Complex temporary = array[firstRowIndex][x];
            array[firstRowIndex][x] = array[secondRowIndex][x];
            array[secondRowIndex][x] = temporary;
        }
    }

    public void swapCols(int firstColIndex, int secondColIndex) {
        for (int y = 0; y < array.length; y++) {
            Complex temporary = array[y][firstColIndex];
            array[y][firstColIndex] = array[y][secondColIndex];
            array[y][secondColIndex] = temporary;
        }
    }

    public void subtractRows(int toIndex, int fromIndex) {
        for (int x = 0; x < array[0].length; x++) {
            array[toIndex][x].sub(array[fromIndex][x]);
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
        for (Complex[] c : array) {
            int counter = 0;
            for (int x = 0; x < array[0].length - 1; x++) {
                if (Util.equals(c[x], 0.0)) {
                    counter++;
                }
            }
            if (counter == array[0].length - 1) {
                if (!Util.equals(c[array[0].length - 1], 0.0)) {
                    return true; // no solutions
                }
            }
        }
        return false;
    }

    public int zeroRowsCounter() {
        int resultCounter = 0;
        for (Complex[] c : array) {
            boolean isNull = true;
            for (int x = 0; x < array[0].length; x++) {
                if (!Util.equals(c[x], 0.0)) {
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
                divideRow(i, new Complex(array[i][i].getRe(), array[i][i].getIm()));
                for (int y = i + 1; y < array.length; y++) {
                    if (!Util.equals(array[y][i], 0.0)) {
                        divideRow(y, new Complex(array[y][i].getRe(), array[y][i].getIm()));
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
                if (!Util.equals(array[y][i], 0.0)) {
                    Complex c = new Complex(array[y][i].getRe(), array[y][i].getIm());
                    multiplyRow(i, c);
                    subtractRows(y, i);
                    divideRow(i, c);
                }
            }
        }
    }

    public Complex[] getNewColMultiplied(int colIndex, double value) {
        Complex[] res = new Complex[array.length];
        for (int y = 0; y < array.length; y++) {
            Complex temporary = new Complex(array[y][colIndex].getRe(), array[y][colIndex].getIm());
            temporary.multiply(value);
            res[y] = temporary;
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
        for (Complex[] c : array) {
            for (int x = 0; x < array[0].length; x++) {
                res.append(c[x]).append(' ');
            }
            res.append('\n');
        }
        return res.toString();
    }
}
