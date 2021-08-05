package com.epam.jwd.secondtask.services.calculation;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.services.PlaneValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;


public class PerpendicularityCalculator {

    private final static Logger perpendicularLogger = LogManager.getLogger(PerpendicularityCalculator.class);

    public static boolean isPlanePerpendicularOxy(Plane plane) {
        try {
            PlaneValidator.checkPlane(plane);
        } catch (Exception ex) {
            perpendicularLogger.error(ex);
            throw ex;
        }
        if (plane.getCoefficientB().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        if (plane.getCoefficientA().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientA().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return false;
    }

    public static boolean isPlanePerpendicularOxz(Plane plane) {
        try {
            PlaneValidator.checkPlane(plane);
        } catch (Exception ex) {
            perpendicularLogger.error(ex);
            throw ex;
        }
        if (plane.getCoefficientB().compareTo(plane.getCoefficientA()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        if (plane.getCoefficientB().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return false;
    }

    public static boolean isPlanePerpendicularOyz(Plane plane) {
        try {
            PlaneValidator.checkPlane(plane);
        } catch (Exception ex) {
            perpendicularLogger.error(ex);
            throw ex;
        }
        if (plane.getCoefficientB().compareTo(plane.getCoefficientA()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        if (plane.getCoefficientA().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientA().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return false;
    }

}
