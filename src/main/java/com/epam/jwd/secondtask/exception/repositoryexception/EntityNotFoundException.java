package com.epam.jwd.secondtask.exception.repositoryexception;

public class EntityNotFoundException extends RuntimeException {

    private final static String ENTITY_NOT_FOUND_MCG = "Entity did not found in repository";

    public EntityNotFoundException() {
        super(ENTITY_NOT_FOUND_MCG);
    }

    public EntityNotFoundException(String mcg) {
        super(ENTITY_NOT_FOUND_MCG + String.format(": %s", mcg));
    }
}
