package com.epam.jwd.thirdtask.service;

import com.epam.jwd.thirdtask.exception.InValidExpressionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Stack;
import java.util.function.ToIntBiFunction;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class PolishNoteCalculator {

    private final static Logger LOG = LogManager.getLogger(Interpreter.class);
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

    public int calculate(List<String> expression){
        //todo:validate
        final Pattern digitPattern = Pattern.compile("\\d+");
        final Stack<Integer> stack = new Stack<>();
        Integer arg1;
        Integer arg2;
        for (String unit : expression) {
            if(digitPattern.matcher(unit).matches()){
                stack.push(Integer.valueOf(unit));
                continue;
            }
            if(isBinaryOperator(unit)){
                //head of stack is second param in function
                arg2 = stack.pop();
                arg1 = stack.pop();
                stack.push(findBiFunctionByOperator(unit).applyAsInt(arg1, arg2));
                continue;
            }
            //todo: check is unit unary operation
            stack.push(findUnaryFunctionByOperator(unit).apply(stack.pop()));
        }
        if(stack.size()!=1){
            throw new InValidExpressionException();
        }
        return stack.pop();
    }

    private boolean isBinaryOperator(String unit){
        return true;
    }

    private ToIntBiFunction<Integer, Integer> findBiFunctionByOperator(String operator){
        switch(operator){
            case "+":
                return Integer::sum;
            case "-":
                return (x,y) -> x-y;
            case "*":
                return (x,y) -> x*y;
            case "/":
                return (x,y) -> x/y;
            case "%":
                return (x,y) -> x%y;
            case ">>":
                return (x,y) -> x>>y;
            case "<<":
                return (x,y) -> x<<y;
            case ">>>":
                return (x,y) -> x>>>y;
            case "&":
                return (x,y) -> x&y;
            case "^":
                return (x,y) -> x^y;
            case "|":
                return (x,y) -> x|y;
            default:
                throw new IllegalArgumentException();
        }
    }

    private UnaryOperator<Integer> findUnaryFunctionByOperator(String operator){
        switch(operator){
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
                throw new IllegalArgumentException();
        }
    }
}
