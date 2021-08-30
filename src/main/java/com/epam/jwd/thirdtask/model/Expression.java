package com.epam.jwd.thirdtask.model;

public class Expression extends Lexeme {

    public Expression(String value) {
        super(value);
    }

    @Override
    public String getText() {
        return calculateExpression();
    }

    private String calculateExpression() {
        return null;
    }
}
