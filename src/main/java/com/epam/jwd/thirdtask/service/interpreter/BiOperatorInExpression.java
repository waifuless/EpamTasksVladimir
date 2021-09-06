package com.epam.jwd.thirdtask.service.interpreter;

import java.util.Stack;
import java.util.function.ToIntBiFunction;

public class BiOperatorInExpression implements MinimalUnitInExpression {

    private final ToIntBiFunction<Integer, Integer> function;

    public BiOperatorInExpression(ToIntBiFunction<Integer, Integer> function) {
        this.function = function;
    }

    @Override
    public void interpret(Stack<Integer> contextStack) {
        //head of stack is second param in function
        Integer arg2 = contextStack.pop();
        Integer arg1 = contextStack.pop();
        contextStack.push(function.applyAsInt(arg1, arg2));
    }
}
