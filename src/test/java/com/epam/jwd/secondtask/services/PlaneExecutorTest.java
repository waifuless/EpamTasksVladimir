package com.epam.jwd.secondtask.services;

import com.epam.jwd.secondtask.exceptions.PlaneConstructedException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.Point;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class PlaneExecutorTest {

    @DataProvider
    public Object[][] createPlaneFromThreePointsData() {
        return new Object[][]{
                {Point.of(1, 2, 1), Point.of(4, 1, 2),
                        Point.of(8, 9, -3), Plane.of(3, -19, -28, 63)},
                {Point.of(1, -2, 1), Point.of(-4, 1, 2),
                        Point.of(0, 9, -3), Plane.of(23, 21, 52, -33)}
        };
    }

    @Test(dataProvider = "createPlaneFromThreePointsData")
    public void testCreatePlaneFromThreePoints(Point point1, Point point2, Point point3, Plane expected) {
        Plane plane = PlaneExecutor.createPlaneFromThreePoints(point1, point2, point3);
        Assert.assertEquals(plane, expected);
    }


    @DataProvider
    public Object[][] constructedExceptionData(){
        return new Object[][]{
                {Point.of(1,1,1), Point.of(2,2,2), Point.of(3,3,3)},
                {Point.of(0,0,0),Point.of(0,0,0),Point.of(0,0,0)},
                {null, null, null}
        };
    }

    @Test(expectedExceptions = PlaneConstructedException.class, dataProvider = "constructedExceptionData")
    public void testConstructedException(Point point1, Point point2, Point point3) {
        PlaneExecutor.createPlaneFromThreePoints(point1, point2, point3);
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