package org.ikinsure.machine;

import org.ikinsure.machine.machine.GenericMachine;
import org.ikinsure.machine.machine.Machine;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.ikinsure.machine.Ingredient.*;
import static org.ikinsure.machine.Ingredient.CUPS;
import static org.ikinsure.machine.Product.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MachineTest {

    @ParameterizedTest
    @MethodSource("generator")
    void shouldMachineWorks(String[] expected, String[] inputs) throws Exception {

        Map<Ingredient, Integer> supplies = new LinkedHashMap<>();
        supplies.put(WATER, 400);
        supplies.put(MILK, 540);
        supplies.put(BEANS, 120);
        supplies.put(CUPS, 9);

        withTextFromSystemIn(inputs).execute(() -> {

            String[] actual = tapSystemOutNormalized(() -> {

                Machine machine = new GenericMachine(
                        "coffee",
                        new Scanner(System.in)::nextLine,
                        System.out::println,
                        supplies,
                        new Product[]{CAPPUCCINO, LATTE, ESPRESSO},
                        550
                );
                machine.run();

            }).split("\n");

            assertArrayEquals(actual, expected);
        });
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(
                        new String[]{
                                "Write action (buy, fill, take, remaining, exit): ",
                                "400 of water",
                                "540 of milk",
                                "120 of coffee beans",
                                "9 of disposable cups",
                                "$550 of money",
                                "Write action (buy, fill, take, remaining, exit): ",
                                "What do you want to buy? 1 - cappuccino, 2 - latte, 3 - espresso, back - to main menu: ",
                                "I have enough resources, making you a coffee!",
                                "Write action (buy, fill, take, remaining, exit): ",
                                "50 of water",
                                "465 of milk",
                                "100 of coffee beans",
                                "8 of disposable cups",
                                "$557 of money",
                                "Write action (buy, fill, take, remaining, exit): ",
                                "What do you want to buy? 1 - cappuccino, 2 - latte, 3 - espresso, back - to main menu: ",
                                "Sorry, not enough water!",
                                "Write action (buy, fill, take, remaining, exit): ",
                                "I gave you $557",
                                "Write action (buy, fill, take, remaining, exit): ",
                                "Write how many ml of water do you want to add: ",
                                "Write how many ml of milk do you want to add: ",
                                "Write how many grams of coffee beans do you want to add: ",
                                "Write how many disposable cups of coffee do you want to add: ",
                                "Write action (buy, fill, take, remaining, exit): ",
                                "What do you want to buy? 1 - cappuccino, 2 - latte, 3 - espresso, back - to main menu: ",
                                "I have enough resources, making you a coffee!",
                                "Write action (buy, fill, take, remaining, exit): ",
                                "300 of water",
                                "370 of milk",
                                "88 of coffee beans",
                                "9 of disposable cups",
                                "$6 of money",
                                "Write action (buy, fill, take, remaining, exit): "
                        },
                        new String[]{"remaining", "buy", "2",
                                "remaining", "buy", "3", "take",
                                "fill", "500", "5", "0", "2",
                                "buy", "1", "remaining", "exit"}
                )
        );
    }
}
