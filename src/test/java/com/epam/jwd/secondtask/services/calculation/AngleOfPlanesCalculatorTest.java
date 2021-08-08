package com.epam.jwd.secondtask.services.calculation;

import com.epam.jwd.secondtask.model.Plane;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AngleOfPlanesCalculatorTest {

    @DataProvider
    public Object[][] calculateAngleBetweenPlanesData() {
        return new Object[][]{
                {Plane.of(1, 1, 1, 0),
                        Plane.of(2, 2, 2, 0), BigDecimal.ZERO},
                {Plane.of(0, 2, 1, 2),
                        Plane.of(22, 21, -8, 0), BigDecimal.valueOf(61.08580854)}
        };
    }

    @Test(dataProvider = "calculateAngleBetweenPlanesData")
    public void testCalculateAngleBetweenPlanes(Plane plane1, Plane plane2, BigDecimal angle) {
        Assert.assertEquals(AngleOfPlanesCalculator.calculateAngleBetweenPlanes(plane1, plane2)
                        .setScale(4, RoundingMode.HALF_UP),
                angle.setScale(4, RoundingMode.HALF_UP));
    }
}