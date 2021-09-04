package com.epam.jwd.thirdtask.exception;

public class InValidExpressionException extends RuntimeException {

    public InValidExpressionException() {
    }

    public InValidExpressionException(String mcg) {
        super(mcg);
    }
}
