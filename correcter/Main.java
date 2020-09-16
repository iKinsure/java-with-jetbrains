package correcter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] data = readFromFile("send.txt");
        generateError(data);
        saveToFile("received.txt", data);
    }

    private static void generateError(byte[] data) {
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] ^= 1 << random.nextInt(Byte.SIZE);
        }
    }

    private static byte[] readFromFile(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(fileName));
    }

    private static void saveToFile(String fileName, byte[] data) throws IOException {
        FileOutputStream output = new FileOutputStream(new File("received.txt"));
        output.write(data);
        output.close();
    }

    private static String removeError(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 2; i < text.length(); i += 3) {
            char a = text.charAt(i - 2);
            char b = text.charAt(i - 1);
            char c = text.charAt(i);
            if (a == b) {
                result.append(a);
            }
            else if (a == c) {
                result.append(a);
            }
            else if (b == c) {
                result.append(b);
            }
        }
        return result.toString();
    }

    private static String encode(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(text.charAt(i));
            result.append(text.charAt(i));
            result.append(text.charAt(i));
        }
        return result.toString();
    }

    private static String addError(String text) {
        Random r = new Random();
        StringBuilder result = new StringBuilder(text);
        for (int i = 2; i < result.length(); i += 3) {
            result.setCharAt(i - r.nextInt(3), (char) (r.nextInt(96) + 32));
        }
        return result.toString();
    }
}
