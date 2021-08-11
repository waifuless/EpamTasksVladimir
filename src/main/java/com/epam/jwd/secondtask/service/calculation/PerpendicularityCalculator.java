package com.epam.jwd.secondtask.service.calculation;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.model.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;


public class PerpendicularityCalculator {

    private final static Logger LOG = LogManager.getLogger(PerpendicularityCalculator.class);

    public static boolean isPlanePerpendicularOxy(Plane plane) {
        checkPlaneNotNull(plane);
        return isPlaneParallelOxz(plane) || isPlaneParallelOyz(plane);
    }

    public static boolean isPlanePerpendicularOxz(Plane plane) {
        checkPlaneNotNull(plane);
        return isPlaneParallelOxy(plane) || isPlaneParallelOyz(plane);
    }

    public static boolean isPlanePerpendicularOyz(Plane plane) {
        checkPlaneNotNull(plane);
        return isPlaneParallelOxy(plane) || isPlaneParallelOxz(plane);
    }

    private static void checkPlaneNotNull(Plane plane) {
        if (plane == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    private static boolean isPlaneParallelOxy(Plane plane) {
        return plane.getCoefficientB().compareTo(plane.getCoefficientA()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0;
    }

    private static boolean isPlaneParallelOxz(Plane plane) {
        return plane.getCoefficientA().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientA().compareTo(BigDecimal.ZERO) == 0;
    }

    private static boolean isPlaneParallelOyz(Plane plane) {
        return plane.getCoefficientB().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0;
    }

}
