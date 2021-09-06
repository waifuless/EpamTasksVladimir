package com.epam.jwd.thirdtask.exception;

public class ActionNotSupportedException extends RuntimeException {

    public ActionNotSupportedException() {
    }

    public ActionNotSupportedException(String mcg) {
        super(mcg);
    }
}
