package com.epam.jwd.secondtask.exception.repositoryexception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepositoryExceptionsFactory {


    private final static Logger LOG = LogManager.getLogger(RepositoryExceptionsFactory.class);


    public static RuntimeException createAndLogException(ExceptionInRepository exception) {
        RuntimeException ex;
        switch (exception) {
            case INVALID_ID_EXCEPTION:
                ex = new InvalidIdException();
                LOG.error(ex.getMessage(), ex);
                break;
            case ENTITY_NOT_FOUND_EXCEPTION:
                ex = new EntityNotFoundException();
                LOG.warn(ex);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return ex;
    }

    public static RuntimeException createAndLogException(ExceptionInRepository exception, String mcg) {
        RuntimeException ex;
        switch (exception) {
            case INVALID_ID_EXCEPTION:
                ex = new InvalidIdException(mcg);
                LOG.error(ex.getMessage(), ex);
                break;
            case ENTITY_NOT_FOUND_EXCEPTION:
                ex = new EntityNotFoundException(mcg);
                LOG.warn(ex.getMessage());
                break;
            default:
                throw new IllegalArgumentException();
        }
        return ex;
    }
}
