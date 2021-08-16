package com.epam.jwd.secondtask.service.calculation;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.validation.PlaneValidationFactory;
import com.epam.jwd.secondtask.validation.ValidationStrategy;

import java.math.BigDecimal;


public class PerpendicularityCalculator {

    private final static ValidationStrategy planeValidator = PlaneValidationFactory
            .getValidationStrategy(PlaneValidationFactory.WayToValidPlane.VALIDATE_WITH_EXCEPTIONS);

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
        checkPlane(plane);
        return isPlaneParallelOxz(plane) || isPlaneParallelOyz(plane);
    }

    public boolean isPlanePerpendicularOxz(Plane plane) {
        checkPlane(plane);
        return isPlaneParallelOxy(plane) || isPlaneParallelOyz(plane);
    }

    public boolean isPlanePerpendicularOyz(Plane plane) {
        checkPlane(plane);
        return isPlaneParallelOxy(plane) || isPlaneParallelOxz(plane);
    }

    private void checkPlane(Plane plane) {
        planeValidator.isPlaneValid(plane);
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
