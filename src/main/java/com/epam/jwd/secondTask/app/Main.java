package com.epam.jwd.secondTask.app;

import com.epam.jwd.secondTask.model.*;
import com.epam.jwd.secondTask.services.PlaneExecutor;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Point pointA = Point.of(1.5, 1.2, -10);
        Point pointB = Point.of(2, 6.2, 2);
        Point pointC = Point.of(0, -5, 2);

        Plane plane = PlaneExecutor.createPlaneFromThreePoints(pointA, pointB, pointC);

        System.out.println(plane);
    }
}
