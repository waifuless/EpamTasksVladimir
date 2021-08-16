package com.epam.jwd.secondtask.validation;

public class PlaneValidationFactory {

    private static PlaneBooleanValidator booleanValidator;
    private static PlaneExceptionValidator exceptionValidator;

    public static ValidationStrategy getValidationStrategy(WayToValidPlane wayToValidPlane) {
        switch (wayToValidPlane) {
            case VALIDATE_WITH_BOOL:
                if (booleanValidator == null) {
                    booleanValidator = new PlaneBooleanValidator();
                }
                return booleanValidator;
            case VALIDATE_WITH_EXCEPTIONS:
                if (exceptionValidator == null) {
                    exceptionValidator = new PlaneExceptionValidator();
                }
                return exceptionValidator;
            default:
                throw new IllegalArgumentException();
        }
    }

    public enum WayToValidPlane {
        VALIDATE_WITH_EXCEPTIONS,
        VALIDATE_WITH_BOOL
    }
}
