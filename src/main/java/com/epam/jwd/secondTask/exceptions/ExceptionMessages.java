package com.epam.jwd.secondTask.exceptions;

public enum ExceptionMessages {

    POINT_IS_NULL_MCG("received point(s) is(are) null"),
    NORMAL_VECTOR_IS_ZERO_MCG("the normal vector of the plane is zero"),
    ALL_COEFFICIENTS_ARE_ZERO_MCG("all coefficients are zero"),
    ARGUMENT_IS_NULL_MCG("received argument(s) is(are) null");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
