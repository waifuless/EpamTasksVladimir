package com.epam.jwd.secondtask.services.calculation;

import com.epam.jwd.secondtask.model.Plane;

import java.math.BigDecimal;


public class PerpendicularityCalculator {

    public static boolean isPlanePerpendicularOxy(Plane plane) {
        return isPlaneParallelOxz(plane) || isPlaneParallelOyz(plane);
    }

    public static boolean isPlanePerpendicularOxz(Plane plane) {
        return isPlaneParallelOxy(plane) || isPlaneParallelOyz(plane);
    }

    public static boolean isPlanePerpendicularOyz(Plane plane) {
        return isPlaneParallelOxy(plane) || isPlaneParallelOxz(plane);
    }

    private static boolean isPlaneParallelOxy(Plane plane){
        return plane.getCoefficientB().compareTo(plane.getCoefficientA()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0;
    }

    private static boolean isPlaneParallelOxz(Plane plane){
        return plane.getCoefficientA().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientA().compareTo(BigDecimal.ZERO) == 0;
    }

    private static boolean isPlaneParallelOyz(Plane plane){
        return plane.getCoefficientB().compareTo(plane.getCoefficientC()) == 0
                && plane.getCoefficientB().compareTo(BigDecimal.ZERO) == 0;
    }

}
