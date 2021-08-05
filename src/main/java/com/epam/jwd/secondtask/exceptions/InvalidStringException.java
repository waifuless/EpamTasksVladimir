package com.epam.jwd.secondtask.exceptions;

public class InvalidStringException extends RuntimeException {

    private final static String ERROR_MCG = "String: '%s' does not match the pattern";

    public InvalidStringException(String invalidString) {
        super(String.format(ERROR_MCG, invalidString));
    }
}
