package hard.solver;

import java.io.IOException;
import java.util.Arrays;

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
        double[] result = new GaussElimination().solve(matrix);
        System.out.println("The solution is: " + Arrays.toString(result));
        FileOperator.saveToFile(fileOut, Utility.toString(result));
        System.out.println("Saved to file " + fileOut);
    }
}
