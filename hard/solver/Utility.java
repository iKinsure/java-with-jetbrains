package hard.solver;

public class Utility {

    public static double[][] toDoubleArray(String input) {
        String[] data = input.split("\n");
        int size = Integer.parseInt(data[0]);
        double[][] array = new double[size][size + 1];
        for (int i = 1; i < data.length; i++) {
            String[] row = data[i].split(" ");
            for (int j = 0; j < row.length; j++) {
                array[i - 1][j] = Double.parseDouble(row[j]);
            }
        }
        return array;
    }

    public static String toStringResult(double[] input) {
        StringBuilder result = new StringBuilder();
        for (double i : input) {
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public static boolean equals(double a, double b) {
        return Double.compare(a, b) == 0;
    }
}
