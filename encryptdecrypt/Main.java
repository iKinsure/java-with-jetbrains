package encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {

        // init
        String mode = "enc";
        int key = 0;
        String data = "";
        String in = null;
        String out = null;

        // read input
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-mode":
                        mode = args[i + 1];
                        break;
                    case "-key":
                        key = Integer.parseInt(args[i + 1]);
                        break;
                    case "-data":
                        data = args[i + 1];
                        break;
                    case "-in":
                        in = args[i + 1];
                        break;
                    case "-out":
                        out = args[i + 1];
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred: input invalid");
            return;
        }

        // logic
        if ("dec".equals(mode)) {
            key = -key;
        }
        if (in != null && "".equals(data)) {
            try {
                data = readFile(in);
            } catch (IOException e) {
                System.out.println("Error occurred: failed to read file");
                return;
            }
        }
        if (out == null) {
            System.out.println(crypt(data, key));
        } else {
            try {
                saveToFile(crypt(data, key), out);
            } catch (IOException e) {
                System.out.println("Error occurred: failed to save");
                return;
            }
        }
    }

    private static String crypt(String text, int key) {
        return text.chars().map(c -> c + key).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append).toString();
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
