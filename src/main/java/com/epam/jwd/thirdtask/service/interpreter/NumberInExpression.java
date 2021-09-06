package com.epam.jwd.thirdtask.service.interpreter;

import java.util.Stack;

public class NumberInExpression implements MinimalUnitInExpression {

    private final Integer value;

    public NumberInExpression(Integer value) {
        this.value = value;
    }

    @Override
    public void interpret(Stack<Integer> contextStack) {
        contextStack.push(value);
    }
}
