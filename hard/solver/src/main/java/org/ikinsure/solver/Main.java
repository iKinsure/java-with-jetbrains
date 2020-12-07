package org.ikinsure.solver;

import java.io.IOException;

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
        Matrix matrix = new Matrix(Util.toComplexArray(data));
        System.out.println("Start solving the equation.");
        GaussElimination algorithm = new GaussElimination();
        Complex[] result = algorithm.solve(matrix);
        System.out.println(algorithm.getMessage());
        FileOperator.saveToFile(fileOut, result == null ?
                algorithm.getMessage() : Util.toString(result));
        System.out.println("Saved to file " + fileOut);
    }
}
