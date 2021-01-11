package org.ikinsure.contacts;


import java.util.function.BiFunction;
import java.util.function.Function;

public class Test {


    public static void main(String[] args) {

        BiFunction<Integer, Integer, BiFunction<Integer, Integer, Function<Integer, Integer>>> dd =
                (a, b) -> (c, d) -> e -> a + b * c + d - e;

        int x = dd.apply(1, 2).apply(3, 4).apply(5);
        System.out.println(x);
    }

}
