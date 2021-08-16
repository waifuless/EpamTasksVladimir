package com.epam.jwd.secondtask.validation;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.exception.PlaneIsInvalidException;
import com.epam.jwd.secondtask.model.Plane;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class PlaneExceptionValidatorTest {

    private final ValidationStrategy planeValidator = PlaneValidationFactory
            .getValidationStrategy(PlaneValidationFactory.WayToValidPlane.VALIDATE_WITH_EXCEPTIONS);

    @DataProvider
    public Object[][] InvalidPlaneData() {
        Plane plane1 = Plane.of(1, 0, 0, 1);
        plane1.setCoefficientA(BigDecimal.ZERO);
        Plane plane2 = Plane.of(0, 1, 0, 0);
        plane2.setCoefficientB(BigDecimal.ZERO);
        return new Object[][]{
                {plane1},
                {plane2}
        };
    }

    @Test(dataProvider = "InvalidPlaneData", expectedExceptions = PlaneIsInvalidException.class)
    public void testInvalidPlaneExceptions(Plane plane) {
        planeValidator.isPlaneValid(plane);
    }

    @Test(expectedExceptions = ArgumentNullException.class)
    public void testNullArgumentException() {
        planeValidator.isPlaneValid(null);
    }
}