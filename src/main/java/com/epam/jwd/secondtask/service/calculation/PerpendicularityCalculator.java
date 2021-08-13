package com.epam.jwd.secondtask.service.calculation;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.model.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;


public class PerpendicularityCalculator {

    private final static Logger LOG = LogManager.getLogger(PerpendicularityCalculator.class);

    private static PerpendicularityCalculator instance;

    private PerpendicularityCalculator() {
    }

    public static PerpendicularityCalculator getInstance() {
        if (instance == null) {
            instance = new PerpendicularityCalculator();
        }
        return instance;
    }

    public boolean isPlanePerpendicularOxy(Plane plane) {
        checkPlaneNotNull(plane);
        return isPlaneParallelOxz(plane) || isPlaneParallelOyz(plane);
    }

    public boolean isPlanePerpendicularOxz(Plane plane) {
        checkPlaneNotNull(plane);
        return isPlaneParallelOxy(plane) || isPlaneParallelOyz(plane);
    }

    public boolean isPlanePerpendicularOyz(Plane plane) {
        checkPlaneNotNull(plane);
        return isPlaneParallelOxy(plane) || isPlaneParallelOxz(plane);
    }

    private void checkPlaneNotNull(Plane plane) {
        if (plane == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    private boolean isPlaneParallelOxy(Plane plane) {
        return plane.getCoefficientB().compareTo(plane.getCoefficientA()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0;
    }

    private boolean isPlaneParallelOxz(Plane plane) {
        return plane.getCoefficientA().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientA().compareTo(BigDecimal.ZERO) == 0;
    }

    private boolean isPlaneParallelOyz(Plane plane) {
        return plane.getCoefficientB().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0;
    }

}
