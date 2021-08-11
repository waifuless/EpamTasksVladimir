package com.epam.jwd.secondtask.exception;

public enum ExceptionMessages {

    POINT_IS_NULL_MCG("Received point(s) is(are) null"),
    ALL_COEFFICIENTS_ARE_ZERO_MCG("All coefficients are zero"),
    ARGUMENT_IS_NULL_MCG("Received argument(s) is(are) null"),
    STRING_MATCH_ERROR_MCG("String: '%s' does not match the pattern"),
    RUN_OUT_OF_PLANES_MCG("File is run out of planes");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
