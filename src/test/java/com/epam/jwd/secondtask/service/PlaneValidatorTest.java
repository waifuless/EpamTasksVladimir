package com.epam.jwd.secondtask.service;

import com.epam.jwd.secondtask.exception.PlaneConstructedException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class PlaneValidatorTest {

    @DataProvider
    public Object[][] constructedExceptionData(){
        return new Object[][]{
                {null, null, null, null},
                {BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO},
                {BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(2)}
        };
    }

    @Test(expectedExceptions = PlaneConstructedException.class, dataProvider = "constructedExceptionData")
    public void testCheckCoefficients(BigDecimal cA, BigDecimal cB, BigDecimal cC, BigDecimal freeTerm) {
        PlaneValidator.checkCoefficients(cA, cB, cC, freeTerm);
    }

    //test that exception will not be received
    @Test
    public void testCheckCoefficients2() {
        PlaneValidator.checkCoefficients(BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.ZERO);
    }


}