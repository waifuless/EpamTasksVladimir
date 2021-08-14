package com.epam.jwd.secondtask.validation;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.exception.ExceptionMessages;
import com.epam.jwd.secondtask.exception.PlaneIsInvalidException;
import com.epam.jwd.secondtask.model.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class PlaneExceptionValidator implements ValidationStrategy {

    private final static Logger LOG = LogManager.getLogger(PlaneBooleanValidator.class);

    PlaneExceptionValidator() {
    }

    @Override
    public boolean isPlaneValid(Plane plane) {
        if (plane == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
        if (plane.getCoefficientA() == null || plane.getCoefficientB() == null
                || plane.getCoefficientC() == null || plane.getFreeTerm() == null) {
            PlaneIsInvalidException ex = new PlaneIsInvalidException(ExceptionMessages.PLANE_HAS_NULL_MCG);
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
        if (plane.getCoefficientA().compareTo(plane.getCoefficientB()) == 0
                && plane.getCoefficientA().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientA().compareTo(BigDecimal.ZERO) == 0) {
            PlaneIsInvalidException ex = new PlaneIsInvalidException(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG);
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
        return true;
    }
}
