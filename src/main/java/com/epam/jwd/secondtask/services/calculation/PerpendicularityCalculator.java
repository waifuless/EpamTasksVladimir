package com.epam.jwd.secondtask.services.calculation;

import com.epam.jwd.secondtask.model.Plane;

import java.math.BigDecimal;


public class PerpendicularityCalculator {

    public static boolean isPlanePerpendicularOxy(Plane plane) {
        if (plane.getCoefficientB().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return plane.getCoefficientA().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientA().compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isPlanePerpendicularOxz(Plane plane) {
        if (plane.getCoefficientB().compareTo(plane.getCoefficientA()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return plane.getCoefficientB().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isPlanePerpendicularOyz(Plane plane) {
        if (plane.getCoefficientB().compareTo(plane.getCoefficientA()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return plane.getCoefficientA().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientA().compareTo(BigDecimal.ZERO) == 0;
    }

}
