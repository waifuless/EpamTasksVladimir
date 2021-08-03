package com.epam.jwd.secondTask.model;

import com.epam.jwd.secondTask.exceptions.ExceptionMessages;
import com.epam.jwd.secondTask.exceptions.PlaneConstructedException;

import java.math.BigDecimal;

public class Plane {

    //The coefficients of the equation of the plane
    private final BigDecimal coefficientA;
    private final BigDecimal coefficientB;
    private final BigDecimal coefficientC;
    private final BigDecimal freeTerm;

    Plane(BigDecimal coefficientA, BigDecimal coefficientB, BigDecimal coefficientC, BigDecimal freeTerm) {
        if (coefficientA == null || coefficientB == null || coefficientC == null || freeTerm == null) {
            throw new PlaneConstructedException(ExceptionMessages.ARGUMENT_IS_NULL_MCG);
        }
        if (coefficientA.compareTo(coefficientB)==0 && coefficientA.compareTo(coefficientC)==0
                && coefficientA.compareTo(BigDecimal.ZERO)==0) {
            throw new PlaneConstructedException(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG);
        }
        this.coefficientA = coefficientA;
        this.coefficientB = coefficientB;
        this.coefficientC = coefficientC;
        this.freeTerm = freeTerm;
    }

    public static Plane of(BigDecimal coefficientA, BigDecimal coefficientB,
                           BigDecimal coefficientC, BigDecimal freeTerm) {
        return new Plane(coefficientA, coefficientB, coefficientC, freeTerm);
    }

    public static Plane of(String coefficientA, String coefficientB, String coefficientC, String freeTerm) {
        return new Plane(new BigDecimal(coefficientA), new BigDecimal(coefficientB),
                new BigDecimal(coefficientC), new BigDecimal(freeTerm));
    }

    public static Plane of(double coefficientA, double coefficientB, double coefficientC, double freeTerm) {
        return new Plane(BigDecimal.valueOf(coefficientA), BigDecimal.valueOf(coefficientB),
                BigDecimal.valueOf(coefficientC), BigDecimal.valueOf(freeTerm));
    }

    public BigDecimal getCoefficientA() {
        return coefficientA;
    }

    public BigDecimal getCoefficientB() {
        return coefficientB;
    }

    public BigDecimal getCoefficientC() {
        return coefficientC;
    }

    public BigDecimal getFreeTerm() {
        return freeTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (coefficientA.compareTo(plane.coefficientA)!=0) return false;
        if (coefficientB.compareTo(plane.coefficientB)!=0) return false;
        if (coefficientC.compareTo(plane.coefficientC)!=0) return false;
        return freeTerm.compareTo(plane.freeTerm)==0;
    }

    @Override
    public int hashCode() {
        int result = coefficientA.hashCode();
        result = 31 * result + coefficientB.hashCode();
        result = 31 * result + coefficientC.hashCode();
        result = 31 * result + freeTerm.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "coefficientA=" + coefficientA +
                ", coefficientB=" + coefficientB +
                ", coefficientC=" + coefficientC +
                ", freeTerm=" + freeTerm +
                '}';
    }
}
