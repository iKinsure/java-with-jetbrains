package hard.solver;

import java.io.IOException;

/**
 * Main method for stage 4
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String fileIn = null;
        String fileOut = null;
        for (int i = 0; i < args.length; i += 2) {
            if ("-in".equals(args[i])) {
                fileIn = args[i + 1];
            }
            if ("-out".equals(args[i])) {
                fileOut = args[i + 1];
            }
        }
        String data = FileOperator.readFromFile(fileIn);
        Matrix matrix = new Matrix(Utility.toDoubleArray(data));
        System.out.println("Start solving the equation.");
        GaussElimination algorithm = new GaussElimination();
        double[] result = algorithm.solve(matrix);
        System.out.println(algorithm.getMessage());
        if (result != null) {
            FileOperator.saveToFile(fileOut, Utility.toString(result));
        } else {
            FileOperator.saveToFile(fileOut, algorithm.getMessage());
        }
        System.out.println("Saved to file " + fileOut);
    }
}
