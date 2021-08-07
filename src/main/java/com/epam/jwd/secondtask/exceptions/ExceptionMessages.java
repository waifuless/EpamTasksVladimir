package com.epam.jwd.secondtask.exceptions;

public enum ExceptionMessages {

    POINT_IS_NULL_MCG("Received point(s) is(are) null"),
    ALL_COEFFICIENTS_ARE_ZERO_MCG("All coefficients are zero"),
    ARGUMENT_IS_NULL_MCG("Received argument(s) is(are) null"),
    FIELD_OF_PLANE_IS_NULL_MCG("Field(s) of plane is(are) null"),
    STRING_MATCH_ERROR_MCG("String: '%s' does not match the pattern");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
