package org.ikinsure.correcter;

import java.io.*;
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
        String encodedAdded = add(encoded);
        saveToFileAsByte(ENCODE_FILE, getByteFromBin(encodedAdded));
        System.out.println(SEND_FILE + ":");
        System.out.println("text view: " + getTextFromBin(data));
        System.out.println("hex view: " + getHexFromBin(data));
        System.out.println("bin view: " + getBinWithSpacesFromBin(data) + "\n");
        System.out.println(ENCODE_FILE + ":");
        System.out.println("expand: " + getParityFromBinWithSpaces(getBinWithSpacesFromBin(encodedAdded)));
        System.out.println("parity: " + getBinWithSpacesFromBin(encodedAdded));
        System.out.println("hex view: " + getHexFromBin(encodedAdded));
    }

    private static void sendInstruction() throws IOException {
        String data = getBinFromByte(readFromFile(ENCODE_FILE));
        String error = send(data);
        saveToFileAsByte(RECEIVE_FILE, getByteFromBin(error));
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
        saveToFileAsByte(DECODE_FILE, getByteFromBin(decodedRemove));
        System.out.println(RECEIVE_FILE + ":");
        System.out.println("hex view: " + getHexFromBin(data));
        System.out.println("bin view: " + getBinWithSpacesFromBin(data) + "\n");
        System.out.println(DECODE_FILE + ":");
        System.out.println("correct: " + getBinWithSpacesFromBin(encode(decoded)));
        System.out.println("decode: " + getBinWithSpacesFromBin(decoded));
        System.out.println("hex view: " + getHexFromBin(decodedRemove));
        System.out.println("text view: " + getTextFromBin(decodedRemove));
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

    private static String getParityFromBinWithSpaces(String data) {
        StringBuilder result = new StringBuilder(data);
        for (int i = 0; i < data.length(); i += (Byte.SIZE + 1)) {
            result.setCharAt(i, '.');
            result.setCharAt(i + 1, '.');
            result.setCharAt(i + 3, '.');
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

    private static String getTextFromBin(String data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length(); i += Byte.SIZE) {
            result.append((char) Integer.parseInt(data.substring(i, Byte.SIZE + i), 2));
        }
        return result.toString();
    }

    private static byte[] getByteFromBin(String data) {
        String[] bytes = data.split("(?<=\\G.{8})");
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) Integer.parseInt(bytes[i], 2);
        }
        return result;
    }

    /** ===================================================================== */

    private static String encode(String data) {
        StringBuilder result = new StringBuilder();
        for (int i = 3; i < data.length(); i += 4) {
            int s1 = Character.getNumericValue(data.charAt(i - 3));
            int s2 = Character.getNumericValue(data.charAt(i - 2));
            int s3 = Character.getNumericValue(data.charAt(i - 1));
            int s4 = Character.getNumericValue(data.charAt(i));
            int p1 = (s1 + s2 + s4) % 2;
            int p2 = (s1 + s3 + s4) % 2;
            int p3 = (s2 + s3 + s4) % 2;
            result.append(p1).append(p2).append(s1).append(p3).
                    append(s2).append(s3).append(s4).append("0");
        }
        return result.toString();
    }

    private static String decode(String data) {
        StringBuilder result = new StringBuilder();
        for (int i = Byte.SIZE - 1; i < data.length(); i += Byte.SIZE) {
            int errorPos = 0;
            int[] h = new int[8];
            for (int j = 0; j < 8; j++) {
                h[j] = Character.getNumericValue(data.charAt(j + i - 7));
            }
            if (h[0] != (h[2] + h[4] + h[6]) % 2) {
                errorPos += 1;
            }
            if (h[1] != (h[2] + h[5] + h[6]) % 2) {
                errorPos += 2;
            }
            if (h[3] != (h[4] + h[5] + h[6]) % 2) {
                errorPos += 4;
            }
            if (errorPos != 0) {
                h[errorPos - 1] = h[errorPos - 1] == 1 ? 0 : 1;
            }
            result.append(h[2]).append(h[4]).append(h[5]).append(h[6]);
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

    private static String add(String data) {
        StringBuilder result = new StringBuilder(data);
        while (result.length() % Byte.SIZE != 0) {
            result.append("0");
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
