package encryptdecrypt;

import java.util.Scanner;

public class Main {

    enum Mode {
        ENC(false), DEC(true);
        boolean isReading;
        Mode(boolean isReading) {
            this.isReading = isReading;
        }
    }

    public static void main(String[] args) throws Exception {

        // init
        Mode mode = Mode.ENC;
        int key = 0;
        String data = "";

        // read input
        try {
            for (int i = 0; i < args.length; i++) {
                if ("-mode".equals(args[i])) {
                    mode = Mode.valueOf(args[i + 1].toUpperCase());
                }
                else if ("-key".equals(args[i])) {
                    key = Integer.parseInt(args[i + 1]);
                }
                else if ("-data".equals(args[i])) {
                    data = args[i + 1];
                }
            }
        } catch (Exception e) {
            throw new Exception("Input error");
        }

        // logic
        System.out.println(crypt(data, key, mode.isReading));
    }

    private static String crypt(String text, int key, boolean isReading) {
        StringBuilder builder = new StringBuilder(text);
        for (int i = 0; i < builder.length(); i++) {
            builder.setCharAt(i, (char) (builder.charAt(i) + (isReading ? - key : key)));
        }
        return builder.toString();
    }
}
