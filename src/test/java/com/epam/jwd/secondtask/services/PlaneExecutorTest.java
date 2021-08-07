package com.epam.jwd.secondtask.services;

import com.epam.jwd.secondtask.exceptions.PlaneConstructedException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlaneExecutorTest {

    @Test
    public void testCreatePlaneFromThreePoints() {
        Point point1 = Point.of(1, 2, 1);
        Point point2 = Point.of(4, 1, 2);
        Point point3 = Point.of(8, 9, -3);
        Plane plane = PlaneExecutor.createPlaneFromThreePoints(point1,point2,point3);
        Assert.assertEquals(plane, Plane.of(3, -19, -28, 63));
    }

    @Test
    public void testCreatePlaneFromThreePoints2() {
        Point point1 = Point.of(1, -2, 1);
        Point point2 = Point.of(-4, 1, 2);
        Point point3 = Point.of(0, 9, -3);
        Plane plane = PlaneExecutor.createPlaneFromThreePoints(point1,point2,point3);
        Assert.assertEquals(plane, Plane.of(23, 21, 52, -33));
    }

    @Test(expectedExceptions = PlaneConstructedException.class)
    public void testConstructedException(){
        Point point1 = Point.of(1, 1, 1);
        Point point2 = Point.of(2, 2, 2);
        Point point3 = Point.of(3, 3, 3);
        Plane plane = PlaneExecutor.createPlaneFromThreePoints(point1,point2,point3);
    }

    @Test(expectedExceptions = PlaneConstructedException.class)
    public void testConstructedException2(){
        Point point1 = Point.of(0, 0, 0);
        Point point2 = Point.of(0, 0, 0);
        Point point3 = Point.of(0, 0, 0);
        Plane plane = PlaneExecutor.createPlaneFromThreePoints(point1,point2,point3);
    }

    @Test(expectedExceptions = PlaneConstructedException.class)
    public void testConstructedException3(){
        Plane plane = PlaneExecutor.createPlaneFromThreePoints(null, null, null);
    }

    @Test
    public void testNormalizeCoefficients() {
        Plane oldPlane = Plane.of(1, 2, 3, 4);
        Assert.assertEquals(PlaneExecutor.normalizeCoefficients(oldPlane), oldPlane);
    }

    @Test
    public void testNormalizeCoefficients2() {
        Plane oldPlane = Plane.of(-60, -24, -79.2, 158.4);
        Assert.assertEquals(PlaneExecutor.normalizeCoefficients(oldPlane), Plane.of(25, 10, 33, -66));
    }
}