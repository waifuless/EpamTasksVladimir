package com.epam.jwd.thirdtask.service.interpreter;

import java.util.Stack;
import java.util.function.UnaryOperator;

public class UnaryOperatorInExpression implements MinimalUnitInExpression {

    private final UnaryOperator<Integer> function;

    public UnaryOperatorInExpression(UnaryOperator<Integer> function) {
        this.function = function;
    }

    @Override
    public void interpret(Stack<Integer> contextStack) {
        contextStack.push(function.apply(contextStack.pop()));
    }
}
