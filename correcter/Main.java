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
        System.out.print("Write a mode: ");
        String mode = SCANNER.next();
        System.out.println();
        switch (mode.toLowerCase()) {
            case "encode":
                encodeInstruction();
                break;
            case "send":
                sendInstruction();
                break;
            case "decode":
                decodeInstruction();
                break;
            default:
        }
    }

    /** ===================================================================== */

    private static void encodeInstruction() throws IOException {
        String data = getBinFromByte(readFromFile(SEND_FILE));
        String encoded = encode(data);
        saveToFileAsByte(ENCODE_FILE, getByteFromBinString(encoded));
        System.out.println(SEND_FILE + ":");
        System.out.println("text view: " + getTextStringFromBinString(data));
        System.out.println("hex view: " + getBinWithSpacesFromBin(data));
        System.out.println("bin view: " + getBinWithSpacesFromBin(data) + "\n");
        System.out.println(ENCODE_FILE + ":");
        System.out.println("expand: " + getParityString(getBinWithSpacesFromBin(encoded)));
        System.out.println("parity: " + getBinWithSpacesFromBin(encoded));
        System.out.println("hex view: " + getBinWithSpacesFromBin(encoded));
    }

    private static void sendInstruction() throws IOException {
        String data = getBinFromByte(readFromFile(ENCODE_FILE));
        String error = send(data);
        saveToFileAsByte(RECEIVE_FILE, getByteFromBinString(error));
        System.out.println(ENCODE_FILE + ":");
        System.out.println("hex view: " + getHexFromBin(data));
        System.out.println("bin view: " + getBinWithSpacesFromBin(data) + "\n");
        System.out.println(RECEIVE_FILE + ":");
        System.out.println("bin view: " + getBinWithSpacesFromBin(error));
        System.out.println("hex view: " + getHexFromBin(error));
    }

    private static void decodeInstruction() throws IOException {
        String data = getBinFromByte(readFromFile(RECEIVE_FILE));
        String decoded = decode(data);
        String decodedRemove = remove(decoded);
        saveToFileAsByte(DECODE_FILE, getByteFromBinString(decodedRemove));
        System.out.println(RECEIVE_FILE + ":");
        System.out.println("hex view: " + getHexFromBin(data));
        System.out.println("bin view: " + getBinWithSpacesFromBin(data) + "\n");
        System.out.println(DECODE_FILE + ":");
        System.out.println("correct: " + getBinWithSpacesFromBin(encode(decoded)));
        System.out.println("decode: " + getBinWithSpacesFromBin(decoded));
        System.out.println("remove: " + getBinWithSpacesFromBin(decodedRemove));
        System.out.println("hex view: " + getHexFromBin(decodedRemove));
        System.out.println("text view: " + getTextStringFromBinString(decodedRemove));
    }

    /** ===================================================================== */

    private static String getHexFromBin(String data) {
        StringBuilder result = new StringBuilder();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            if (i % 4 == 0 && i != 0) {
                int decimal = Integer.parseInt(builder.toString(), 2);
                String hex = Integer.toString(decimal, 16).toUpperCase();
                result.append(hex);
                if (i % 8 == 0) {
                    result.append(" ");
                }
                builder.delete(0, 4);
            }
            builder.append(data.charAt(i));
        }
        int decimal = Integer.parseInt(builder.toString(), 2);
        String hex = Integer.toString(decimal, 16).toUpperCase();
        result.append(hex);
        return result.toString();
    }

    private static String getBinWithSpacesFromBin(String data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            if (i % 8 == 0 && i != 0) {
                result.append(" ");
            }
            result.append(data.charAt(i));
        }
        return result.toString();
    }

    private static String getParityString(String data) {
        StringBuilder result = new StringBuilder(data);
        for (int i = 0; i < data.length(); i += (Byte.SIZE + 1)) {
            result.setCharAt(i + 6, '.');
            result.setCharAt(i + 7, '.');
        }
        return result.toString();
    }

    private static String getBinFromByte(byte[] data) {
        StringBuilder result = new StringBuilder();
        for (byte datum : data) {
            for (int j = Byte.SIZE - 1; j >= 0 ; j--) {
                result.append((datum >> j) & 1);
            }
        }
        return result.toString();
    }

    private static String getTextStringFromBinString(String data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length(); i += Byte.SIZE) {
            result.append((char) Integer.parseInt(data.substring(i, Byte.SIZE + i), 2));
        }
        return result.toString();
    }

    private static byte[] getByteFromBinString(String data) {
        String[] bytes = data.split("(?<=\\G.{8})");
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) Integer.parseInt(bytes[i], 2);
        }
        return result;
    }

    /** ===================================================================== */

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
        if (data.length() % 3 == 2) {
            byte a = Byte.parseByte(String.valueOf(data.charAt(data.length() - 2)));
            byte b = Byte.parseByte(String.valueOf(data.charAt(data.length() - 1)));
            byte c = 0;
            result.append("00");
            result.append(a ^ b ^ c).append(a ^ b ^ c);
        }
        if (data.length() % 3 == 1) {
            byte a = Byte.parseByte(String.valueOf(data.charAt(data.length() - 1)));
            byte b = 0;
            byte c = 0;
            result.append("0000");
            result.append(a ^ b ^ c).append(a ^ b ^ c);
        }
        while (result.length() % Byte.SIZE != 0) {
            result.append("0");
        }
        return result.toString();
    }

    private static String send(String data) {
        Random random = new Random();
        StringBuilder result = new StringBuilder(data);
        for (int i = Byte.SIZE - 1; i < data.length(); i += Byte.SIZE) {
            int pos = i - random.nextInt(Byte.SIZE);
            result.setCharAt(pos, result.charAt(pos) == '1' ? '0' : '1');
        }
        return result.toString();
    }

    private static String remove(String data) {
        StringBuilder result = new StringBuilder(data);
        while (result.length() % Byte.SIZE  != 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /** ===================================================================== */

    private static byte[] readFromFile(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(fileName));
    }

    private static void saveToFileAsByte(String fileName, byte[] data) throws IOException {
        FileOutputStream output = new FileOutputStream(new File(fileName));
        output.write(data);
        output.close();
    }
}
