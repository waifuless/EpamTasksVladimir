package com.epam.jwd.secondtask.model;

import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.testng.Assert.*;

public class PlaneRegistrarTest {

    @Test
    public void testInitialization(){
        PlaneRegistrar registrar = new PlaneRegistrar(1, "lol",
                Plane.of(2,2,2,2));
        assertEquals(registrar.getAngleWithOxy().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(54.7356).setScale(4, RoundingMode.HALF_UP));
        assertEquals(registrar.getAngleWithOxz().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(54.7356).setScale(4, RoundingMode.HALF_UP));
        assertEquals(registrar.getAngleWithOyz().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(54.7356).setScale(4, RoundingMode.HALF_UP));
    }

    @Test
    public void testPropertyChange() {
        PlaneRegistrar registrar = new PlaneRegistrar(1, "lol",
                Plane.of(2,2,2,2));
        registrar.getPlane().setCoefficientA(BigDecimal.valueOf(4));

        assertEquals(registrar.getAngleWithOxy().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(65.9052).setScale(4, RoundingMode.HALF_UP));
        assertEquals(registrar.getAngleWithOxz().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(65.9052).setScale(4, RoundingMode.HALF_UP));
        assertEquals(registrar.getAngleWithOyz().setScale(4, RoundingMode.HALF_UP),
                BigDecimal.valueOf(35.2644).setScale(4, RoundingMode.HALF_UP));
    }
}