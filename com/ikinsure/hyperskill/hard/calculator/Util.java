package com.ikinsure.hyperskill.hard.calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Util {

    /**
     * creates a postfix notation from input
     * @param values collection of command without whitespaces and variables
     * @return list with postfix notation
     * @throws IllegalStateException when undefined symbol or too many brackets
     */
    static List<String> postfixNotation(Collection<String> values) throws IllegalStateException {
        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<String> braces = new ArrayDeque<>();
        ArrayList<String> result = new ArrayList<>();
        for (String val : values) {
            if (val.matches("-?\\d+")) { // add all numbers
                result.add(val);
            }  else if (val.matches("[-+*/^]")) { // operators
                if (stack.isEmpty() || stack.getLast().equals("(")) {
                    stack.offerLast(val);
                } else if (getPriority(val) > getPriority(stack.getLast())) {
                    stack.offerLast(val);
                } else if (getPriority(val) <= getPriority(stack.getLast())){
                    while (!stack.isEmpty()) {
                        String last = stack.pollLast();
                        if (getPriority(last) < getPriority(val) || last.equals("(")) {
                            break;
                        }
                        if (last.matches("[-+*/^]")) {
                            result.add(last);
                        }
                    }
                    stack.offerLast(val);
                }
            } else if (val.equals("(")) { // bracket "("
                stack.offerLast(val);
                braces.offerLast(val);
            } else if (val.equals(")")) { // bracket ")"
                if (braces.pollLast() == null) { // illegal brackets quantity
                    throw new IllegalStateException("Invalid expression");
                }
                while (!stack.isEmpty()) {
                    String last = stack.pollLast();
                    if (last.equals("(")) {
                        break;
                    }
                    if (last.matches("[-+*/]")) {
                        result.add(last);
                    }
                }
            } else { // ?
                throw new IllegalStateException("Invalid identifier");
            }
        }
        stack.descendingIterator().forEachRemaining(s -> {
            if (s.matches("[-+*/^]")) {
                result.add(s);
            }
        });
        if (braces.size() != 0) { // illegal brackets quantity
            throw new IllegalStateException("Invalid expression");
        }
        return result;
    }

    /**
     * calculate result of postfix equation
     * @param values expression in postfix
     * @return result as long
     */
    static long calculatePostfix(List<String> values) {
        ArrayDeque<Long> stack = new ArrayDeque<>();
        for (String val : values) {
            if (val.matches("-?\\d+")) {
                stack.offerLast(Long.parseLong(val));
            } else {
                Long aLong = stack.pollLast();
                Long bLong = stack.pollLast();
                long a = aLong == null ? 0 : aLong;
                long b = bLong == null ? 0 : bLong;
                switch (val) {
                    case "+":
                        stack.offerLast(b + a);
                        break;
                    case "-":
                        stack.offerLast(b - a);
                        break;
                    case "*":
                        stack.offerLast(b * a);
                        break;
                    case "/":
                        stack.offerLast(b / a);
                        break;
                    case "^":
                        stack.offerLast((long) Math.pow(b, a));
                        break;
                }
            }
        }
        return stack.removeLast();
    }

    /**
     * sets a priority for operator
     * @param operator operation sign
     * @return priority as integer
     */
    static int getPriority(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        }
        if (operator.equals("*") || operator.equals("/")) {
            return 2;
        }
        if (operator.equals("^")) {
            return 3;
        }
        return -1;
    }
}
