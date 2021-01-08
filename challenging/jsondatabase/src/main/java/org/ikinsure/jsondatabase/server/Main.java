package org.ikinsure.jsondatabase.server;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


    private static final String[] db = new String[100];

    public static void main(String[] args) {
        Arrays.fill(db, "");
        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("exit")) {
            System.out.println(execute(command));
        }
    }

    private static String execute(String command) {
        String[] params = command.split("\\s+", 3);
        if ("get".equals(params[0])) {
            int index = getIndex(params[1]);
            if (checkIndex(index) && !db[index].isEmpty()) {
                return db[index];
            }
        } else if ("set".equals(params[0])) {
            int index = getIndex(params[1]);
            if (checkIndex(index)) {
                db[index] = params[2];
                return "OK";
            }
        } else if ("delete".equals(params[0])) {
            int index = getIndex(params[1]);
            if (checkIndex(index)) {
                db[index] = "";
                return "OK";
            }
        }
        return "ERROR";
    }

    private static boolean checkIndex(int index) {
        return index < db.length && index >= 0;
    }

    private static int getIndex(String number) {
        return Integer.parseInt(number) - 1;
    }
}
