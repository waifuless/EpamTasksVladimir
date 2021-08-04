package com.epam.jwd.secondtask.exceptions;

public class InvalidStringException extends RuntimeException {

    public InvalidStringException() {
        super("String does not match the pattern");
    }

    public InvalidStringException(String mcg){
        super(mcg);
    }
}
