package com.epam.jwd.secondtask.exception.repositoryexception;

public class InvalidIdException extends RuntimeException {

    private final static String INVALID_ID_MCG = "Received ID is invalid";

    public InvalidIdException() {
        super(INVALID_ID_MCG);
    }

    public InvalidIdException(String mcg) {
        super(INVALID_ID_MCG + String.format(": %s", mcg));
    }
}
