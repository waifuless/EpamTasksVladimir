package com.epam.jwd.thirdtask.service;

import com.epam.jwd.thirdtask.exception.InValidExpressionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.ToIntBiFunction;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class PolishNoteCalculator {

    private final static Logger LOG = LogManager.getLogger(Interpreter.class);
    private final static List<String> LIST_OF_BINARY_OPERATORS =
            Arrays.asList("+", "-", "*", "/", "%", ">>", "<<", ">>>", "&", "^", "|");
    private final static Pattern DIGIT_PATTERN = Pattern.compile("\\d+");
    private final static String OPERATOR_NOT_FOUND_MCG = "Operator not found";
    private final static String INVALID_EXPRESSION_MCG = "Given expression is invalid";

    private static volatile PolishNoteCalculator instance;

    private PolishNoteCalculator() {
    }

    public static PolishNoteCalculator getInstance() {
        if (instance == null) {
            synchronized (PolishNoteCalculator.class) {
                if (instance == null) {
                    instance = new PolishNoteCalculator();
                }
            }
        }
        return instance;
    }

    public int calculate(List<String> expression) {
        //todo:validate
        LOG.debug("Expression to calculate: {}", expression);
        final Stack<Integer> stack = new Stack<>();
        Integer arg1;
        Integer arg2;
        for (String unit : expression) {
            if (DIGIT_PATTERN.matcher(unit).matches()) {
                stack.push(Integer.valueOf(unit));
                continue;
            }
            try {
                if (LIST_OF_BINARY_OPERATORS.contains(unit)) {
                    //head of stack is second param in function
                    arg2 = stack.pop();
                    arg1 = stack.pop();
                    stack.push(findBiFunctionByOperator(unit).applyAsInt(arg1, arg2));
                    continue;
                }
                stack.push(findUnaryFunctionByOperator(unit).apply(stack.pop()));
            } catch (ArithmeticException ex) {
                LOG.error(ex.getMessage(), ex);
                throw ex;
            } catch (Exception ex) {
                LOG.error(INVALID_EXPRESSION_MCG, ex);
                throw ex;
            }
        }
        if (stack.size() != 1) {
            InValidExpressionException ex = new InValidExpressionException(expression.toString());
            LOG.error(INVALID_EXPRESSION_MCG, ex);
            throw ex;
        }
        LOG.debug("Result of calculating: {}", stack.peek());
        return stack.pop();
    }

    private ToIntBiFunction<Integer, Integer> findBiFunctionByOperator(String operator) {
        switch (operator) {
            case "+":
                return Integer::sum;
            case "-":
                return (x, y) -> x - y;
            case "*":
                return (x, y) -> x * y;
            case "/":
                return (x, y) -> x / y;
            case "%":
                return (x, y) -> x % y;
            case ">>":
                return (x, y) -> x >> y;
            case "<<":
                return (x, y) -> x << y;
            case ">>>":
                return (x, y) -> x >>> y;
            case "&":
                return (x, y) -> x & y;
            case "^":
                return (x, y) -> x ^ y;
            case "|":
                return (x, y) -> x | y;
            default:
                IllegalArgumentException ex = new IllegalArgumentException(operator);
                LOG.error(OPERATOR_NOT_FOUND_MCG, ex);
                throw ex;
        }
    }

    private UnaryOperator<Integer> findUnaryFunctionByOperator(String operator) {
        switch (operator) {
            case "!":
                return x -> {
                    int result = 1;
                    for (int i = 1; i <= x; i++) {
                        result = result * i;
                    }
                    return result;
                };
            case "~":
                return x -> ~x;
            default:
                IllegalArgumentException ex = new IllegalArgumentException(operator);
                LOG.error(OPERATOR_NOT_FOUND_MCG, ex);
                throw ex;
        }
    }
}
