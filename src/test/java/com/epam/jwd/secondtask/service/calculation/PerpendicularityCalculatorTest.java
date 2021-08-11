package com.epam.jwd.secondtask.service.calculation;

import com.epam.jwd.secondtask.model.Plane;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PerpendicularityCalculatorTest {

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
        Assert.assertEquals(PerpendicularityCalculator.isPlanePerpendicularOxy(plane), answer);
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
        Assert.assertEquals(PerpendicularityCalculator.isPlanePerpendicularOxz(plane), answer);
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
        Assert.assertEquals(PerpendicularityCalculator.isPlanePerpendicularOyz(plane), answer);
    }
}