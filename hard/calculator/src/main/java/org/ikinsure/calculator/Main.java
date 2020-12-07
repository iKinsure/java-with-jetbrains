package org.ikinsure.calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static final Scanner SCANNER = new Scanner(System.in);
    static final Map<String, BigDecimal> vars = new LinkedHashMap<>(); // variables in memory

    static final String EQUATION_REGEX = "([A-Za-z]+\\s*=)?(\\s*[-+(]*\\s*)*(((\\d+(\\.\\d+)?)|[A-Za-z]+)(((\\s*[-+()]+\\s*)+)|(\\s*[()]*\\s*[*/^]+\\s*[()]*\\s*)))*(\\d+(\\.\\d+)?|[A-Za-z]+)\\)*";
    static final String DECIMAL_REGEX = "\\d+(\\.\\d+)?";
    static final String DECIMAL_WITH_MINUS_REGEX = "-?" + DECIMAL_REGEX;
    static final String VARIABLE_REGEX = "[A-Za-z]+";
    static final String OPERATOR_REGEX = "[-+*/^()]";

    static final String COMMAND_SIGN = "/";
    static final String EXIT = "exit";
    static final String HELP = "help";

    public static void main(String[] args) {
        while (true) {
            String order = clean(SCANNER.nextLine());
            if (order.matches(EQUATION_REGEX)) {
                try {
                    if (order.contains("=")) {
                        String[] eq = order.split("=");
                        if (vars.containsKey(eq[0] )) {
                            vars.replace(eq[0] , eval(eq[1]));
                        } else {
                            vars.put(eq[0] , eval(eq[1]));
                        }
                    } else {
                        System.out.println(eval(order));
                    }
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            } else if (order.equals(COMMAND_SIGN + EXIT)) {
                System.out.println("Bye!");
                break;
            } else if (order.equals(COMMAND_SIGN + HELP)) {
                System.out.println("The program calculates equations");
            } else if (order.isBlank()) {

            } else {
                if (order.startsWith(COMMAND_SIGN)) {
                    System.out.println("Unknown command");
                } else if (order.contains("=")) {
                    System.out.println("Invalid identifier");
                } else {
                    System.out.println("Invalid expression");
                }
            }
        }
    }

    // decode existing variables
    private static Map<Integer, String> decode(Map<Integer, String> variables) {
        for (var entry : variables.entrySet()) {
            Integer k = entry.getKey();
            String v = entry.getValue();
            if (vars.containsKey(v)) {
                variables.replace(k, String.valueOf(vars.get(v)));
            } else {
                throw new IllegalStateException("Unknown variable");
            }
        }
        return variables;
    }

    // prepare data structure from command and get result
    private static BigDecimal eval(String command) throws IllegalStateException {
        Map<Integer, String> map = new TreeMap<>();
        map.putAll(find(DECIMAL_REGEX, command));
        map.putAll(decode(find(VARIABLE_REGEX, command)));
        map.putAll(find(OPERATOR_REGEX, command));
        List<String> list = Util.postfixNotation(map.values());
        return Util.calculatePostfix(list);
    }

    // return map with positions and found regex result
    private static Map<Integer, String> find(String regex, String command) {
        Map<Integer, String> results = new TreeMap<>();
        Matcher matcher = Pattern.compile(regex).matcher(command);
        while (matcher.find()) {
            results.put(matcher.start(), matcher.group());
        }
        return results;
    }

    // remove whitespaces and doubled signs
    private static String clean(String command) {
        return command.replaceAll("\\s+", "")
                .replaceAll("(--)+", "+")
                .replaceAll("\\++", "+")
                .replaceAll("(\\+-)+", "-")
                .replaceAll("(-\\+)+", "-")
                .replaceAll("\\*+", "*")
                .replaceAll("/+", "/");
    }
}
