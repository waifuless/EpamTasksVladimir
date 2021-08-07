package com.epam.jwd.secondtask.services;

import com.epam.jwd.secondtask.exceptions.ExceptionMessages;
import com.epam.jwd.secondtask.exceptions.PlaneConstructedException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.Point;
import com.epam.jwd.secondtask.services.calculation.GcdCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlaneExecutor {

    private final static int NUMBER_OF_COEFFICIENTS_IN_PLANE = 4;
    private final static int COMMON_DIVIDE_SCALE = 8;

    private final static Logger LOG = LogManager.getLogger(PlaneExecutor.class);

    public static Plane createPlaneFromThreePoints(Point firstP, Point secondP, Point thirdP) {
        if (firstP == null || secondP == null || thirdP == null) {
            PlaneConstructedException ex = new PlaneConstructedException(ExceptionMessages.POINT_IS_NULL_MCG);
            LOG.error(ex);
            throw ex;
        }

        //Calculate from formula, I made on paper
        BigDecimal coefficientA = (secondP.getY().subtract(firstP.getY()))
                .multiply(thirdP.getZ().subtract(firstP.getZ())).subtract
                        ((thirdP.getY().subtract(firstP.getY())).multiply(secondP.getZ().subtract(firstP.getZ())));
        BigDecimal coefficientB = (secondP.getX().subtract(firstP.getX()))
                .multiply(thirdP.getZ().subtract(firstP.getZ())).subtract
                        ((thirdP.getX().subtract(firstP.getX())).multiply(secondP.getZ().subtract(firstP.getZ())));
        BigDecimal coefficientC = (secondP.getX().subtract(firstP.getX()))
                .multiply(thirdP.getY().subtract(firstP.getY())).subtract
                        ((thirdP.getX().subtract(firstP.getX())).multiply(secondP.getY().subtract(firstP.getY())));
        BigDecimal freeTerm = firstP.getX().negate().multiply(coefficientA).add
                (firstP.getY().multiply(coefficientB)).subtract(firstP.getZ().multiply(coefficientC));


        Plane newPlane = Plane.of(coefficientA, coefficientB.negate(), coefficientC, freeTerm);
        //Second coefficient with minus, by my formula I made on paper
        return normalizeCoefficients(newPlane);
    }


    public static Plane normalizeCoefficients(Plane oldPlane) {
        BigDecimal[] arrayOfCoefficients = new BigDecimal[NUMBER_OF_COEFFICIENTS_IN_PLANE];

        arrayOfCoefficients[0] = oldPlane.getCoefficientA();
        arrayOfCoefficients[1] = oldPlane.getCoefficientB();
        arrayOfCoefficients[2] = oldPlane.getCoefficientC();
        arrayOfCoefficients[3] = oldPlane.getFreeTerm();

        long maxScaleLength = 0;
        for (BigDecimal coefficient : arrayOfCoefficients) {
            if (maxScaleLength < coefficient.scale()) {
                maxScaleLength = coefficient.scale();
            }
        }

        //multiply by 10^maxScaleLen to get the integers
        if (maxScaleLength > 0) {
            for (int i = 0; i < arrayOfCoefficients.length; i++) {
                arrayOfCoefficients[i] = arrayOfCoefficients[i]
                        .multiply(BigDecimal.valueOf(Math.pow(10, maxScaleLength)));
            }
        }

        //Find the greatest common divisor of all coefficients
        long gcdValue = arrayOfCoefficients[0].abs().longValue();
        for (int i = 1; i < arrayOfCoefficients.length; i++) {
            gcdValue = GcdCalculator.findGcd(gcdValue, arrayOfCoefficients[i].abs().longValue());
        }

        if (gcdValue > 1) {
            for (int i = 0; i < arrayOfCoefficients.length; i++) {
                arrayOfCoefficients[i] = arrayOfCoefficients[i].divide(BigDecimal.valueOf(gcdValue),
                        COMMON_DIVIDE_SCALE, RoundingMode.HALF_UP);
            }
        }

        return Plane.of(arrayOfCoefficients[0], arrayOfCoefficients[1], arrayOfCoefficients[2], arrayOfCoefficients[3]);
    }


}
