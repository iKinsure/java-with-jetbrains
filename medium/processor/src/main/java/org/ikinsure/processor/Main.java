package org.ikinsure.processor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Processor processor = new Processor(new View(
                System.out::println,
                scanner::nextInt,
                scanner::nextDouble
        ));
        processor.run();
    }

}
