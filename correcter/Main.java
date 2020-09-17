package correcter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        String data = formatToString(readFromFile("send.txt"));
        String encoded = encode(data);
        String error = generateError(encoded);
        String decoded = decode(error);

        System.out.println(data);
        System.out.println(encoded);
        System.out.println(error);
        System.out.println(decoded);
    }

    private static String decode(String data) {
        StringBuilder result = new StringBuilder();
        int errorPos = 0;
        boolean error = false;
        for (int i = 1; i < data.length(); i += 2) {
            if ((i + 1) % 8 == 0 && error) {
                byte a = Byte.parseByte(String.valueOf(data.charAt(i - 6)));
                byte b = Byte.parseByte(String.valueOf(data.charAt(i - 4)));
                byte c = Byte.parseByte(String.valueOf(data.charAt(i - 2)));
                byte d = Byte.parseByte(String.valueOf(data.charAt(i)));
                switch ((errorPos + 1) % 8) {
                    case 2:
                        result.setCharAt(errorPos, (((b ^ c ^ d) == 1) ? '1' : '0'));
                        break;
                    case 4:
                        result.setCharAt(errorPos, (((a ^ c ^ d) == 1) ? '1' : '0'));
                        break;
                    case 6:
                        result.setCharAt(errorPos, (((a ^ b ^ d) == 1) ? '1' : '0'));
                        break;
                }
                error = false;
            } else {
                result.append(data.charAt(i));
                if (data.charAt(i) != data.charAt(i - 1)) {
                    errorPos = result.length() - 1;
                    error = true;
                }
            }
        }
        return result.toString();
    }

    private static String formatToString(byte[] data) {
        StringBuilder result = new StringBuilder();
        for (byte datum : data) {
            for (int j = 0; j < Byte.SIZE; j++) {
                result.append((datum >> j) & 1);
            }
        }
        return result.toString();
    }

    private static String encode(String data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            result.append(data.charAt(i));
            result.append(data.charAt(i));
            if (i % 3 == 2) {
                byte a = Byte.parseByte(String.valueOf(data.charAt(i - 2)));
                byte b = Byte.parseByte(String.valueOf(data.charAt(i - 1)));
                byte c = Byte.parseByte(String.valueOf(data.charAt(i)));
                result.append(a ^ b ^ c);
                result.append(a ^ b ^ c);
            }
        }

        System.out.println(data.length()*3);
        System.out.println(result.length());
        int missingSize = 3 * data.length() - result.length();
        result.append("0".repeat(missingSize));
        return result.toString();
    }



    private static void generateError(byte[] data) {
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] ^= 1 << random.nextInt(Byte.SIZE);
        }
    }

    private static String generateError(String data) {
        Random random = new Random();
        StringBuilder result = new StringBuilder(data);
        for (int i = Byte.SIZE - 1; i < data.length(); i += Byte.SIZE) {
            int pos = i - random.nextInt(Byte.SIZE);
            result.setCharAt(pos, result.charAt(pos) == '1' ? '0' : '1');
        }
        return result.toString();
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

    private static String addError(String text) {
        Random r = new Random();
        StringBuilder result = new StringBuilder(text);
        for (int i = 2; i < result.length(); i += 3) {
            result.setCharAt(i - r.nextInt(3), (char) (r.nextInt(96) + 32));
        }
        return result.toString();
    }
}
