package com.ikinsure.hyperskill.hard.calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Map<String, Long> vars = new LinkedHashMap<>();
    private static final String EQUATION = "([A-Za-z]+\\s*=)?[-+\\s]*((\\d+|[A-Za-z]+)(\\s*[-+]+\\s*)+)*(\\d+|[A-Za-z]+)";

    public static void main(String[] args) {

        while (true) {
            String order = SCANNER.nextLine().strip();
            if (order.matches(EQUATION)) {
                if (order.contains("=")) {
                    try {
                        eval(clean(order));
                    } catch (IllegalStateException e) {
                        System.out.println("Unknown variable");
                    }
                } else {
                    try {
                        System.out.println(eval(clean(order)));
                    } catch (IllegalStateException e) {
                        System.out.println("Unknown variable");
                    }
                }
            } else if (order.equals("/exit")) {
                System.out.println("Bye!");
                break;
            } else if (order.equals("/help")) {
                System.out.println("The program calculates the sum of numbers");
            } else if (order.isBlank()) {

            } else {
                if (order.startsWith("/")) {
                    System.out.println("Unknown command");
                } else if (order.contains("=")) {
                    System.out.println("Invalid identifier");
                } else {
                    System.out.println("Invalid expression");
                }
            }
        }
    }

    private static long eval(String command) throws IllegalStateException {

        // prepare data structure from command
        Map<Long, String> equations = new TreeMap<>();
        Map<Long, String> numbers = find("\\d+", command);
        Map<Long, String> variables = find("[A-Za-z]+", command);
        Map<Long, String> operators = find("[-=+]", command);

        // check if needs assignment in the end
        String variable = "";
        if (command.contains("=")) {
            variable = variables.get(0L);
            variables.remove(0L);
            operators.remove((long) command.indexOf("="));
        }

        // check if there is undeclared var
        for (var entry : variables.entrySet()) {
            Long k = entry.getKey();
            String v = entry.getValue();
            if (vars.containsKey(v)) {
                variables.replace(k, String.valueOf(vars.get(v)));
            } else {
                throw new IllegalStateException();
            }
        }

        // copy data to one map
        equations.putAll(numbers);
        equations.putAll(variables);
        equations.putAll(operators);

        // solve the equation
        long counter = 0;
        String builder = "";
        for (String value : equations.values()) {
            if (value.equals("+")) {
                builder = "+";
            } else if (value.equals("-")) {
                builder = "-";
            } else {
                counter += Long.parseLong(builder + value);
                builder = "";
            }
        }

        // new variable if it is needed
        if (!variable.isBlank()) {
            if (vars.containsKey(variable)) {
                vars.replace(variable, counter);
            } else {
                vars.put(variable, counter);
            }
        }
        return counter;
    }

    private static Map<Long, String> find(String regex, String command) {
        Map<Long, String> results = new TreeMap<>();
        Matcher matcher = Pattern.compile(regex).matcher(command);
        while (matcher.find()) {
            results.put((long) matcher.start(), matcher.group());
        }
        return results;
    }

    private static String clean(String command) {
        return command.replaceAll("\\s+", "")
                .replaceAll("(--)+", "+")
                .replaceAll("\\++", "+")
                .replaceAll("(\\+-)+", "-")
                .replaceAll("(-\\+)+", "-");
    }
}
