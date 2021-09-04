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
    private final static String ORIGIN_EXPRESSION_LOG_MCG = "Origin expression: {}";
    private final static String RESULT_LOG_MCG = "Reverse polish note result: {}";
    private final static String BITWISE_COMPLEMENT = "~";
    private final static String OPENING_BRACKET = "(";
    private final static String CLOSING_BRACKET = ")";
    private final static String FACTORIAL_FUNC = "!";
    private final static Pattern DIGIT_PATTERN = Pattern.compile("\\d+");
    private final static Pattern NOT_WHITE_SPACE_PATTERN = Pattern.compile("\\S+");
    private final static Pattern UNITS_DIVISOR_PATTERN = Pattern.compile("(?<=\\D)|(?=\\D)");

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
        LOG.debug(ORIGIN_EXPRESSION_LOG_MCG, expression);
        String[] units = Arrays.stream(UNITS_DIVISOR_PATTERN.split(expression))
                .filter(s -> NOT_WHITE_SPACE_PATTERN.matcher(s).matches())
                .toArray(String[]::new);
        final Stack<String> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        for (String unit : units) {
            if (unit.equals(FACTORIAL_FUNC) || DIGIT_PATTERN.matcher(unit).matches()) {
                result.add(unit);
                continue;
            }
            if (unit.equals(BITWISE_COMPLEMENT) || unit.equals(OPENING_BRACKET)) {
                stack.push(unit);
                continue;
            }
            if (unit.equals(CLOSING_BRACKET)) {
                if (!stack.contains(OPENING_BRACKET)) {
                    throw new InValidExpressionException();
                }
                while (!stack.peek().equals(OPENING_BRACKET)) {
                    result.add(stack.pop());
                }
                stack.pop();
                continue;
            }
            while (!stack.empty() && findPriority(stack.peek()) > 0 && (stack.peek().equals(BITWISE_COMPLEMENT)
                    || comparePriority(stack.peek(), unit) >= 0)) {
                result.add(stack.pop());
            }
            stack.push(unit);
        }
        //todo: check that in stack only operators
        while (!stack.empty()) {
            result.add(stack.pop());
        }
        LOG.debug(RESULT_LOG_MCG, result);
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
