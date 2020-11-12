package com.ikinsure.hyperskill.hard.sorting;
import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Long> list = new ArrayList<>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            list.add(number);
        }
        System.out.println("Total numbers: " + list.size() + ".");
        long max = Collections.max(list);
        System.out.println("The largest number: " + max + " (" + Collections.frequency(list, max) + " time(s)).");
    }
}
