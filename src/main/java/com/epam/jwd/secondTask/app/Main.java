package com.epam.jwd.secondTask.app;

import com.epam.jwd.secondTask.model.Plane;
import com.epam.jwd.secondTask.model.Point;
import com.epam.jwd.secondTask.services.AngleOfPlanesCalculator;
import com.epam.jwd.secondTask.services.PlaneExecutor;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        /*
        Point pointA = Point.of(2, 1, 2);
        Point pointB = Point.of(2, 6, 22);
        Point pointC = Point.of(21, -5, 2);

        Plane plane = PlaneExecutor.createPlaneFromThreePoints(pointA, pointB, pointC);

        System.out.println(plane);
        Plane plane = Plane.of(6, 0, 0, 2);

        //System.out.println(PerpendicularityCalculator.isPlanePerpendicularOxz(plane));

        System.out.println(AngleOfPlanesCalculator.calculateAngleBetweenPlanes(plane,
                AngleOfPlanesCalculator.OXY_PLANE));

         */

        Plane plane = Plane.of(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ONE,BigDecimal.TEN);
        Plane plane1 = Plane.of(0,0,1,10);

        System.out.println(plane.hashCode()==plane1.hashCode());
    }
}
