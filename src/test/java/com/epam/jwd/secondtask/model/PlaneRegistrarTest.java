package com.epam.jwd.secondtask.model;

import com.epam.jwd.secondtask.exception.PlaneIsInvalidException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.testng.Assert.assertEquals;

public class PlaneRegistrarTest {

    @Test
    public void testInitialization() {
        PlaneRegistrar registrar = new PlaneRegistrar("lol",
                Plane.of(2, 2, 2, 2));
        assertEquals(registrar.getAngleWithOxy().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(54.7356).setScale(4, RoundingMode.HALF_UP));
        assertEquals(registrar.getAngleWithOxz().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(54.7356).setScale(4, RoundingMode.HALF_UP));
        assertEquals(registrar.getAngleWithOyz().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(54.7356).setScale(4, RoundingMode.HALF_UP));
    }

    @Test
    public void testPropertyChange() {
        PlaneRegistrar registrar = new PlaneRegistrar("lol",
                Plane.of(2, 2, 2, 2));
        registrar.getPlane().setCoefficientA(BigDecimal.valueOf(4));

        assertEquals(registrar.getAngleWithOxy().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(65.9052).setScale(4, RoundingMode.HALF_UP));
        assertEquals(registrar.getAngleWithOxz().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(65.9052).setScale(4, RoundingMode.HALF_UP));
        assertEquals(registrar.getAngleWithOyz().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(35.2644).setScale(4, RoundingMode.HALF_UP));
    }

    @Test
    public void testIsPlaneValid() {
        Plane plane = Plane.of(0, 1, 0, 0);
        plane.setCoefficientB(BigDecimal.ZERO); // now should be invalid
        PlaneRegistrar registrar = new PlaneRegistrar("lola", plane);
        Assert.assertFalse(registrar.isPlaneValid());
        plane.setCoefficientB(BigDecimal.TEN);
        Assert.assertTrue(registrar.isPlaneValid());
    }

    @Test(expectedExceptions = PlaneIsInvalidException.class)
    public void testException_getAngle_when_PlaneIsInvalid() {
        Plane plane = Plane.of(0, 1, 0, 0);
        plane.setCoefficientB(BigDecimal.valueOf(0)); // now its invalid
        PlaneRegistrar registrar = new PlaneRegistrar("lola", plane);
        registrar.getAngleWithOxy();
    }
}