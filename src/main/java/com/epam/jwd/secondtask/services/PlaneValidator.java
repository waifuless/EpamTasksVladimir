package com.epam.jwd.secondtask.services;

import com.epam.jwd.secondtask.exceptions.ExceptionMessages;
import com.epam.jwd.secondtask.exceptions.PlaneConstructedException;
import java.math.BigDecimal;

public class PlaneValidator {

    public static void checkCoefficients(BigDecimal coefficientA, BigDecimal coefficientB,
                                         BigDecimal coefficientC, BigDecimal freeTerm){
        if (coefficientA == null || coefficientB == null || coefficientC == null || freeTerm == null) {
            throw new PlaneConstructedException(ExceptionMessages.ARGUMENT_IS_NULL_MCG);
        }
        if (coefficientA.compareTo(coefficientB)==0 && coefficientA.compareTo(coefficientC)==0
                && coefficientA.compareTo(BigDecimal.ZERO)==0) {
            throw new PlaneConstructedException(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG);
        }
    }
}
