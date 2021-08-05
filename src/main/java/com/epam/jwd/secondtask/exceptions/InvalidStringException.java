package com.epam.jwd.secondtask.exceptions;

public class InvalidStringException extends RuntimeException {
    public InvalidStringException(String invalidString) {
        super(String.format(ExceptionMessages.STRING_MATCH_ERROR_MCG.getMessage(), invalidString));
    }

    public InvalidStringException(ExceptionMessages ex){
        super(ex.getMessage());
    }
}
