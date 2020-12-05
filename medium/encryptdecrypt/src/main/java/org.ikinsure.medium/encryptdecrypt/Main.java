package org.ikinsure.medium.encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    /** initial settings */
    static int key = 0;
    static String mode = "enc";
    static String data = "";
    static String in = null;
    static String out = null;
    static String alg = "shift";

    public static void main(String[] args) {
        try {
            getInput(args);
        } catch (IOException e) {
            System.out.println("Error occurred: input invalid");
            return;
        }

        EncodeStrategy encoder = new EncodeStrategy();
        encoder.setMethod(new ShiftEncode());
        if ("unicode".equals(alg)) {
            encoder.setMethod(new UnicodeEncode());
        }
        if ("dec".equals(mode)) {
            key = -key;
        }
        if (in != null && "".equals(data)) {
            try {
                data = readFromFile(in);
            } catch (IOException e) {
                System.out.println("Error occurred: failed to read file");
                return;
            }
        }
        if (out == null) {
            System.out.println(encoder.encode(data, key));
        } else {
            try {
                saveToFile(encoder.encode(data, key), out);
            } catch (IOException e) {
                System.out.println("Error occurred: failed to save");
            }
        }
    }

    private static void getInput(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-mode":
                    mode = args[i + 1];
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
                case "-alg":
                    alg = args[i + 1];
                    break;
            }
        }
    }

    /** -in *file.txt* */
    private static String readFromFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    /** -out *file.txt* */
    private static void saveToFile(String text, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(text);
        }
    }
}
