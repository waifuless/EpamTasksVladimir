package com.epam.jwd.secondtask.services;

import com.epam.jwd.secondtask.exceptions.PlaneConstructedException;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class PlaneValidatorTest {

    @Test(expectedExceptions = PlaneConstructedException.class)
    public void testCheckCoefficients() {
        PlaneValidator.checkCoefficients(null, null, null, null);
    }

    //test that exception will not be received
    @Test
    public void testCheckCoefficients2() {
        PlaneValidator.checkCoefficients(BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.ZERO);
    }

    @Test(expectedExceptions = PlaneConstructedException.class)
    public void testCheckCoefficientsZeroCoefficients() {
        PlaneValidator.checkCoefficients(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    @Test(expectedExceptions = PlaneConstructedException.class)
    public void testCheckCoefficientsZeroCoefficients2() {
        PlaneValidator.checkCoefficients(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(2));
    }

}