package com.epam.jwd.secondtask.validation;

import com.epam.jwd.secondtask.model.Plane;

import java.math.BigDecimal;

public class PlaneBooleanValidator implements ValidationStrategy {

    PlaneBooleanValidator() {
    }

    @Override
    public boolean isPlaneValid(Plane plane) {
        if (plane == null) {
            return false;
        }
        if (plane.getCoefficientA() == null || plane.getCoefficientB() == null
                || plane.getCoefficientC() == null || plane.getFreeTerm() == null) {
            return false;
        }
        return !(plane.getCoefficientA().compareTo(plane.getCoefficientB()) == 0
                && plane.getCoefficientA().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientA().compareTo(BigDecimal.ZERO) == 0);
    }

}
