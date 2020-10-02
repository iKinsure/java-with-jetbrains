package hard.solver;

public class Util {

    public static Complex[][] toComplexArray(String input) {
        String[] data = input.split("\n");
        int variables = Integer.parseInt(data[0].split(" ")[0]);
        int equations = Integer.parseInt(data[0].split(" ")[1]);
        Complex[][] output = new Complex[equations][variables + 1];
        for (int i = 1; i < data.length; i++) {
            String[] row = data[i].split(" ");
            for (int j = 0; j < row.length; j++) {
                final double re;
                final double im;
                if (row[j].substring(1).contains("+") || row[j].substring(1).contains("-")) {
                    int signPos = row[j].contains("+") ? row[j].indexOf('+') : row[j].lastIndexOf('-');
                    re = Double.parseDouble(row[j].substring(0, signPos));
                    String imag = row[j].substring(signPos, row[j].length() - 1);
                    if ("+".equals(imag)) {
                        im = 1.0;
                    } else if ("-".equals(imag)) {
                        im = -1.0;
                    } else {
                        im = Double.parseDouble(imag);
                    }
                } else {
                    if (row[j].contains("i")) {
                        re = 0.0;
                        if (row[j].length() == 1) {
                            im = 1.0;
                        } else if (row[j].length() == 2) {
                            im = -1.0;
                        } else {
                            im = Double.parseDouble(row[j].substring(0, row[j].length() - 1));
                        }
                    } else {
                        re = Double.parseDouble(row[j]);
                        im = 0.0;
                    }
                }
                output[i - 1][j] = new Complex(re, im);
            }
        }
        return output;
    }

    public static String toString(Complex[] input) {
        StringBuilder output = new StringBuilder();
        for (Complex i : input) {
            output.append(i).append("\n");
        }
        return output.toString();
    }

    public static boolean equals(double a, double b) {
        final double THRESHOLD = 0.000001;
        return (Math.abs(a - b) < THRESHOLD);
    }

    public static boolean equals(Complex a, double b) {
        return equals(a.getRe(), b) && equals(a.getIm(), b);
    }
}
