package com.epam.jwd.secondtask.service.calculation;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.model.Plane;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PerpendicularityCalculatorTest {

    private final PerpendicularityCalculator perpendicularityCalculator = PerpendicularityCalculator.getInstance();

    @DataProvider
    public Object[][] perpendicularOxyData() {
        return new Object[][]{
                {Plane.of(0, 0, 1, 0), false},
                {Plane.of(1, 0, 0, 1), true},
                {Plane.of(0, 1, 0, 1), true},
                {Plane.of(1, 2, 3, 1), false}
        };
    }

    @Test(dataProvider = "perpendicularOxyData")
    public void testIsPlanePerpendicularOxy(Plane plane, boolean answer) {
        Assert.assertEquals(perpendicularityCalculator.isPlanePerpendicularOxy(plane), answer);
    }

    @Test(expectedExceptions = ArgumentNullException.class)
    public void testNullArgumentOxy() {
        perpendicularityCalculator.isPlanePerpendicularOxy(null);
    }


    @DataProvider
    public Object[][] perpendicularOxzData() {
        return new Object[][]{
                {Plane.of(0, 0, 1, 0), true},
                {Plane.of(1, 0, 0, 1), true},
                {Plane.of(0, 1, 0, 1), false},
                {Plane.of(1, 2, 3, 1), false}
        };
    }

    @Test(dataProvider = "perpendicularOxzData")
    public void testIsPlanePerpendicularOxz(Plane plane, boolean answer) {
        Assert.assertEquals(perpendicularityCalculator.isPlanePerpendicularOxz(plane), answer);
    }

    @Test(expectedExceptions = ArgumentNullException.class)
    public void testNullArgumentOxz() {
        perpendicularityCalculator.isPlanePerpendicularOxz(null);
    }


    @DataProvider
    public Object[][] perpendicularOyzData() {
        return new Object[][]{
                {Plane.of(0, 0, 1, 0), true},
                {Plane.of(1, 0, 0, 1), false},
                {Plane.of(0, 1, 0, 1), true},
                {Plane.of(1, 2, 3, 1), false}
        };
    }

    @Test(dataProvider = "perpendicularOyzData")
    public void testIsPlanePerpendicularOyz(Plane plane, boolean answer) {
        Assert.assertEquals(perpendicularityCalculator.isPlanePerpendicularOyz(plane), answer);
    }

    @Test(expectedExceptions = ArgumentNullException.class)
    public void testNullArgumentOyz() {
        perpendicularityCalculator.isPlanePerpendicularOyz(null);
    }
}