package com.ikinsure.hyperskill.hard.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(SCANNER.nextLine().split("\\s+")));
        int index = list.indexOf(SCANNER.next()) + 1;
        System.out.println(index == 0 ? "Not found" : index);
    }
}
