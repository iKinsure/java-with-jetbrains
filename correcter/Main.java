package correcter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SEND_FILE = "send.txt";
    private static final String ENCODE_FILE = "encoded.txt";
    private static final String RECEIVE_FILE = "received.txt";
    private static final String DECODE_FILE = "decoded.txt";

    public static void main(String[] args) throws IOException {

        String mode = null;
        String data = null;
        String encoded = null;
        String error = null;
        String decoded = null;

        System.out.print("Write a mode: ");
        mode = SCANNER.next();
        System.out.println();
        switch (mode.toLowerCase()) {
            case "encode":
                data = formatToString(readFromFile(SEND_FILE));
                encoded = encode(data);
                saveToFileAsByte(ENCODE_FILE, formatToByte(encoded));
                showData(data, SEND_FILE);
                showData(encoded, ENCODE_FILE);
                break;
            case "send":
                data = formatToString(readFromFile(ENCODE_FILE));
                error = generateError(data);
                saveToFileAsByte(RECEIVE_FILE, formatToByte(error));
                showData(data, ENCODE_FILE);
                showData(error, RECEIVE_FILE);
                break;
            case "decode":
                data = formatToString(readFromFile(RECEIVE_FILE));
                decoded = decode(data);
                saveToFileAsCharacter(DECODE_FILE, formatToByte(decoded));
                showData(data, RECEIVE_FILE);
                showData(decoded, DECODE_FILE);
                break;
            default:
        }
    }

    private static String decode(String data) {
        StringBuilder result = new StringBuilder();
        int errorPos = 0;
        boolean error = false;
        for (int i = 1; i < data.length(); i += 2) {
            if ((i + 1) % 8 == 0) {
                if (error) {
                    byte a = Byte.parseByte(String.valueOf(data.charAt(i - 6)));
                    byte b = Byte.parseByte(String.valueOf(data.charAt(i - 4)));
                    byte c = Byte.parseByte(String.valueOf(data.charAt(i - 2)));
                    byte d = Byte.parseByte(String.valueOf(data.charAt(i)));
                    switch (errorPos % 3) {
                        case 0:
                            result.setCharAt(errorPos, (((b ^ c ^ d) == 1) ? '1' : '0'));
                            break;
                        case 1:
                            result.setCharAt(errorPos, (((a ^ c ^ d) == 1) ? '1' : '0'));
                            break;
                        case 2:
                            result.setCharAt(errorPos, (((a ^ b ^ d) == 1)  ? '1' : '0'));
                            break;
                    }
                    error = false;
                }
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
            for (int j = Byte.SIZE - 1; j >= 0 ; j--) {
                result.append((datum >> j) & 1);
            }
        }
        return result.toString();
    }

    private static String encode(String data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            result.append(data.charAt(i)).append(data.charAt(i));
            if (i % 3 == 2) {
                byte a = Byte.parseByte(String.valueOf(data.charAt(i - 2)));
                byte b = Byte.parseByte(String.valueOf(data.charAt(i - 1)));
                byte c = Byte.parseByte(String.valueOf(data.charAt(i)));
                result.append(a ^ b ^ c).append(a ^ b ^ c);
            }
        }
        int missingSize = 3 * data.length() - result.length();
        return result.append("0".repeat(missingSize)).toString();
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

    private static byte[] formatToByte(String data) {
        String[] bytes = data.split("(?<=\\G.{8})");
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) Integer.parseInt(bytes[i], 2);
        }
        return result;
    }

    private static void showData(String data, String file) {
        System.out.println(file + ":");
        showHexView(data);
        showBinView(data);
    }

    private static void showBinView(String data) {
        System.out.print("bin view: ");
        for (int i = 0; i < data.length(); i++) {
            if (i % 8 == 0 && i != 0) {
                System.out.print(" ");
            }
            System.out.print(data.charAt(i));
        }
        System.out.println();
    }

    private static void showHexView(String data) {
        System.out.print("hex view: ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            if (i % 4 == 0 && i != 0) {
                int decimal = Integer.parseInt(builder.toString(), 2);
                String hex = Integer.toString(decimal, 16).toUpperCase();
                System.out.print(hex);
                if (i % 8 == 0) {
                    System.out.print(" ");
                }
                builder.delete(0, 4);
            }
            builder.append(data.charAt(i));
        }
        System.out.println();
    }

    private static void saveToFileAsByte(String fileName, byte[] data) throws IOException {
        FileOutputStream output = new FileOutputStream(new File(fileName));
        output.write(data);
        output.close();
    }

    private static void saveToFileAsCharacter(String fileName, byte[] data) throws IOException {
        String text = new String(data, StandardCharsets.UTF_8);
        char[] array = text.toCharArray();
        FileWriter writer = new FileWriter(fileName);
        writer.write(array);
        writer.close();
    }
}
