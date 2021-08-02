package com.epam.jwd.secondTask.services;

import com.epam.jwd.secondTask.model.Plane;
import com.epam.jwd.secondTask.model.Point;

import java.math.BigDecimal;

public class PlaneExecutor {

    private final static int NUMBER_OF_COEFFICIENTS_IN_PLANE = 4;

    public static Plane createPlaneFromThreePoints(Point firstP, Point secondP, Point thirdP) {
        if (!arePointsValidToMakePlane(firstP, secondP, thirdP)) {
            //Error
            throw new RuntimeException();
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

        //Second coefficient with minus, by my formula I made on paper
        return normalizeCoefficients(Plane.of(coefficientA, coefficientB.negate(), coefficientC, freeTerm));
    }


    public static boolean arePointsValidToMakePlane(Point firstP, Point secondP, Point thirdP) {
        if (firstP == null || secondP == null || thirdP == null) {
            return false;
        }

        //Check that points are not equals
        if (firstP.equals(secondP) || firstP.equals(thirdP) || secondP.equals(thirdP)) {
            return false;
        }

        //Check that the points are not on the same line
        if (firstP.getX().equals(secondP.getX()) &&
                firstP.getX().equals(thirdP.getX())) {
            return false;
        }
        if (firstP.getY().equals(secondP.getY()) &&
                firstP.getY().equals(thirdP.getY())) {
            return false;
        }
        if (firstP.getZ().equals(secondP.getZ()) &&
                firstP.getZ().equals(thirdP.getZ())) {
            return false;
        }

        return true;
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
                arrayOfCoefficients[i] = arrayOfCoefficients[i].multiply(BigDecimal.valueOf(Math.pow(10, maxScaleLength)));
            }
        }

        //Find the greatest common divisor of all coefficients
        long gcdValue = arrayOfCoefficients[0].longValue();
        for (int i = 1; i < arrayOfCoefficients.length; i++) {
            gcdValue = GcdCalculator.findGcd(gcdValue, arrayOfCoefficients[i].abs().longValue());
        }


        if (gcdValue > 1) {
            for (int i = 0; i < arrayOfCoefficients.length; i++) {
                arrayOfCoefficients[i] = arrayOfCoefficients[i].divide(BigDecimal.valueOf(gcdValue));
            }
        }

        return Plane.of(arrayOfCoefficients[0], arrayOfCoefficients[1], arrayOfCoefficients[2], arrayOfCoefficients[3]);
    }


}
