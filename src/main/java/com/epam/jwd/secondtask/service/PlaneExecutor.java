package com.epam.jwd.secondtask.service;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.exception.ExceptionMessages;
import com.epam.jwd.secondtask.exception.PlaneConstructedException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlaneExecutor {

    private final static int NUMBER_OF_COEFFICIENTS_IN_PLANE = 4;
    private final static int COMMON_DIVIDE_SCALE = 8;

    private final static Logger LOG = LogManager.getLogger(PlaneExecutor.class);

    private static PlaneExecutor instance;

    private PlaneExecutor() {
    }

    public static PlaneExecutor getInstance() {
        if (instance == null) {
            instance = new PlaneExecutor();
        }
        return instance;
    }

    public Plane createPlaneFromThreePoints(Point firstP, Point secondP, Point thirdP) {
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


    public Plane normalizeCoefficients(Plane oldPlane) {
        if (oldPlane == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }

        BigDecimal[] arrayOfCoefficients = new BigDecimal[NUMBER_OF_COEFFICIENTS_IN_PLANE];
        arrayOfCoefficients[0] = oldPlane.getCoefficientA();
        arrayOfCoefficients[1] = oldPlane.getCoefficientB();
        arrayOfCoefficients[2] = oldPlane.getCoefficientC();
        arrayOfCoefficients[3] = oldPlane.getFreeTerm();

        long maxScaleLength = findMaxScaleLength(arrayOfCoefficients);
        multiplyTheCoefficientsToIntegers(arrayOfCoefficients, maxScaleLength);
        long gcdValue = findGcdOfAllCoefficients(arrayOfCoefficients);
        if (gcdValue > 1) {
            reduceCoefficientsByGcd(arrayOfCoefficients, gcdValue);
        }
        //first coefficient must be positive
        if (arrayOfCoefficients[0].compareTo(BigDecimal.ZERO) < 0) {
            swapAllSigns(arrayOfCoefficients);
        }

        return Plane.of(arrayOfCoefficients[0], arrayOfCoefficients[1], arrayOfCoefficients[2], arrayOfCoefficients[3]);
    }

    private long findMaxScaleLength(BigDecimal[] coefficients) {
        long maxScaleLength = 0;
        for (BigDecimal coefficient : coefficients) {
            if (maxScaleLength < coefficient.scale()) {
                maxScaleLength = coefficient.scale();
            }
        }
        return maxScaleLength;
    }

    private void multiplyTheCoefficientsToIntegers(BigDecimal[] coefficients, long maxScaleLength) {
        //multiply by 10^maxScaleLen to get the integers
        if (maxScaleLength > 0) {
            for (int i = 0; i < coefficients.length; i++) {
                coefficients[i] = coefficients[i]
                        .multiply(BigDecimal.valueOf(Math.pow(10, maxScaleLength)));
            }
        }
    }

    private long findGcdOfAllCoefficients(BigDecimal[] coefficients) {
        //Find the greatest common divisor of all coefficients
        GcdFinder gcdFinder = GcdFinder.getInstance();
        long gcdValue = coefficients[0].abs().longValue();
        for (int i = 1; i < coefficients.length; i++) {
            gcdValue = gcdFinder.findGcd(gcdValue, coefficients[i].abs().longValue());
        }
        return gcdValue;
    }

    private void reduceCoefficientsByGcd(BigDecimal[] coefficients, long gcdValue) {
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = coefficients[i].divide(BigDecimal.valueOf(gcdValue),
                    COMMON_DIVIDE_SCALE, RoundingMode.HALF_UP);
        }
    }

    private void swapAllSigns(BigDecimal[] coefficients) {
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = coefficients[i].negate();
        }
    }
}
