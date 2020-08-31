package encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        String input = null;
        String output = null;

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
                else if ("-in".equals(args[i])) {
                    input = args[i + 1];
                }
                else if ("-out".equals(args[i])) {
                    output = args[i + 1];
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred: input invalid");
            return;
        }

        // logic
        if (input != null && "".equals(data)) {
            try {
                data = readFile(input);
            } catch (IOException e) {
                System.out.println("Error occurred: failed to read file");
                return;
            }
        }
        if (output == null) {
            System.out.println(crypt(data, key, mode.isReading));
        } else {
            try {
                saveToFile(crypt(data, key, mode.isReading), output);
            } catch (IOException e) {
                System.out.println("Error occurred: failed to save");
                return;
            }
        }
    }

    private static String crypt(String text, int key, boolean isReading) {
        StringBuilder builder = new StringBuilder(text);
        for (int i = 0; i < builder.length(); i++) {
            builder.setCharAt(i, (char) (builder.charAt(i) + (isReading ? - key : key)));
        }
        return builder.toString();
    }

    private static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private static void saveToFile(String text, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(text);
        }
    }
}
