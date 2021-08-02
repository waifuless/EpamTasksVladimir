package com.epam.jwd.secondTask.app;

import com.epam.jwd.secondTask.model.*;
import com.epam.jwd.secondTask.services.PlaneExecutor;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Point pointA = Point.of(2, 1, 2);
        Point pointB = Point.of(2, 6, 22);
        Point pointC = Point.of(21, -5, 2);

        Plane plane = PlaneExecutor.createPlaneFromThreePoints(pointA, pointB, pointC);

        System.out.println(plane);
    }
}
