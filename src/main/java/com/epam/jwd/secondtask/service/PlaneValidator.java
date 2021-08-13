package com.epam.jwd.secondtask.service;

import com.epam.jwd.secondtask.exception.ExceptionMessages;
import com.epam.jwd.secondtask.exception.PlaneConstructedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class PlaneValidator {

    private final static Logger LOG = LogManager.getLogger(PlaneValidator.class);

    private static PlaneValidator instance;

    private PlaneValidator() {
    }

    public static PlaneValidator getInstance() {
        if (instance == null) {
            instance = new PlaneValidator();
        }
        return instance;
    }

    public void checkCoefficients(BigDecimal coefficientA, BigDecimal coefficientB,
                                  BigDecimal coefficientC, BigDecimal freeTerm) {
        if (coefficientA == null || coefficientB == null || coefficientC == null || freeTerm == null) {
            LOG.error(ExceptionMessages.ARGUMENT_IS_NULL_MCG.getMessage());
            throw new PlaneConstructedException(ExceptionMessages.ARGUMENT_IS_NULL_MCG);
        }
        if (coefficientA.compareTo(coefficientB) == 0 && coefficientA.compareTo(coefficientC) == 0
                && coefficientA.compareTo(BigDecimal.ZERO) == 0) {
            LOG.error(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG.getMessage());
            throw new PlaneConstructedException(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG);
        }
    }
}
