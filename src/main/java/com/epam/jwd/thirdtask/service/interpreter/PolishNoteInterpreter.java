package com.epam.jwd.thirdtask.service.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntBiFunction;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class PolishNoteInterpreter {

    private final static Logger LOG = LogManager.getLogger(PolishNoteInterpreter.class);
    private final static List<String> LIST_OF_BINARY_OPERATORS =
            Arrays.asList("+", "-", "*", "/", "%", ">>", "<<", ">>>", "&", "^", "|");
    private final static Pattern DIGIT_PATTERN = Pattern.compile("\\d+");
    private final static String ORIGIN_EXPRESSION_LOG_MCG = "Expression to calculate: {}";
    private final static String OPERATOR_NOT_FOUND_MCG = "Operator not found";

    private static volatile PolishNoteInterpreter instance;

    private PolishNoteInterpreter() {
    }

    public static PolishNoteInterpreter getInstance() {
        if (instance == null) {
            synchronized (PolishNoteInterpreter.class) {
                if (instance == null) {
                    instance = new PolishNoteInterpreter();
                }
            }
        }
        return instance;
    }

    public InterpretedExpression interpret(List<String> expression) {
        //todo:validate
        LOG.debug(ORIGIN_EXPRESSION_LOG_MCG, expression);
        InterpretedExpression result = new InterpretedExpression();
        for (String unit : expression) {
            if (DIGIT_PATTERN.matcher(unit).matches()) {
                result.addMinimalUnitToExpression(new NumberInExpression(Integer.valueOf(unit)));
                continue;
            }
            if (LIST_OF_BINARY_OPERATORS.contains(unit)) {
                result.addMinimalUnitToExpression(new BiOperatorInExpression(findBiFunctionByOperator(unit)));
                continue;
            }
            result.addMinimalUnitToExpression(new UnaryOperatorInExpression(findUnaryFunctionByOperator(unit)));
        }
        return result;
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
