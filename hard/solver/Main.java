package hard.solver;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        double [] h = new double[6];
        for (int i = 0; i < h.length; i++) {
            h[i] = SCANNER.nextDouble();
        }
        double y = (h[0] * h[5] - h[2] * h[3]) / (h[0] * h[4] - h[1] * h[3]);
        double x = (h[2] - h[1] * y) / h[0];
        System.out.println(x + " " + y);
    }
}
