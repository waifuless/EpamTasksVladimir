package com.epam.jwd.thirdtask.service;

import com.epam.jwd.thirdtask.exception.InValidExpressionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Interpreter {

    private final static Logger LOG = LogManager.getLogger(Interpreter.class);
    private static volatile Interpreter instance;

    private Interpreter() {
    }

    public static Interpreter getInstance() {
        if (instance == null) {
            synchronized (Interpreter.class) {
                if (instance == null) {
                    instance = new Interpreter();
                }
            }
        }
        return instance;
    }

    public List<String> interpretToPolishNote(String expression) {
        final String bitwiseComplement = "~";
        final String openingBracket = "(";
        final String closingBracket = ")";
        final String factorialFunc = "!";
        List<String> result = new ArrayList<>();
        final Pattern digitPattern = Pattern.compile("\\d+");
        final Pattern notWhiteSpacePattern = Pattern.compile("\\S+");
        String[] units = Arrays.stream(expression.split("(?<=\\D)|(?=\\D)"))
                .filter(s -> notWhiteSpacePattern.matcher(s).matches())
                .toArray(String[]::new);
        LOG.debug(Arrays.toString(units));
        final Stack<String> stack = new Stack<>();
        for (String unit : units) {
            if (unit.equals(factorialFunc) || digitPattern.matcher(unit).matches()) {
                result.add(unit);
                continue;
            }
            if (unit.equals(bitwiseComplement) || unit.equals(openingBracket)) {
                stack.push(unit);
                continue;
            }
            if (unit.equals(closingBracket)) {
                if (!stack.contains(openingBracket)) {
                    throw new InValidExpressionException();
                }
                while (!stack.peek().equals(openingBracket)) {
                    result.add(stack.pop());
                }
                stack.pop();
                continue;
            }
            //todo: check unit is binary operation
            while (!stack.empty() && findPriority(stack.peek())>0 && (stack.peek().equals(bitwiseComplement)
                    || comparePriority(stack.peek(), unit) >= 0)) {
                result.add(stack.pop());
            }
            stack.push(unit);
        }
        LOG.debug(result);
        LOG.debug(stack);
        //todo: check that in stack only operators
        while(!stack.empty()){
            result.add(stack.pop());
        }
        return result;
    }

    private int comparePriority(String operator1, String operator2) {
        //The lower the number, the higher the priority. So compare reversed
        return Integer.compare(findPriority(operator2), findPriority(operator1));
    }

    private int findPriority(String operator) {
        EnumSet<BinaryOperatorsByPriority> enumSet = EnumSet.allOf(BinaryOperatorsByPriority.class);
        for (BinaryOperatorsByPriority elem : enumSet) {
            if (elem.getOperators().contains(operator)) {
                return elem.priority;
            }
        }
        return -1;
    }

    private enum BinaryOperatorsByPriority {
        MULTIPLICATIVE(1, Stream.of("%", "/", "*").collect(Collectors.toList())),
        ADDITIVE(2, Stream.of("-", "+").collect(Collectors.toList())),
        SHIFT(3, Stream.of(">>", "<<", ">>>").collect(Collectors.toList())),
        BITWISE_AND(4, Stream.of("&").collect(Collectors.toList())),
        BITWISE_EXCLUSIVE_OR(5, Stream.of("^").collect(Collectors.toList())),
        BITWISE_INCLUSIVE_OR(6, Stream.of("|").collect(Collectors.toList()));


        final int priority;
        private final List<String> operators;

        BinaryOperatorsByPriority(int priority, List<String> operators) {
            this.priority = priority;
            this.operators = operators;
        }

        public List<String> getOperators() {
            return new ArrayList<>(operators);
        }
    }


}
