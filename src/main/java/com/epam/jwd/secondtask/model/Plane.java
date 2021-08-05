package com.epam.jwd.secondtask.model;

import com.epam.jwd.secondtask.exceptions.ExceptionMessages;
import com.epam.jwd.secondtask.exceptions.PlaneConstructedException;
import com.epam.jwd.secondtask.services.PlaneValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;

import java.math.BigDecimal;

public class Plane {

    //The coefficients of the equation of the plane
    private final BigDecimal coefficientA;
    private final BigDecimal coefficientB;
    private final BigDecimal coefficientC;
    private final BigDecimal freeTerm;

    private final static Logger planeLogger = LogManager.getLogger(Plane.class);

    Plane(BigDecimal coefficientA, BigDecimal coefficientB, BigDecimal coefficientC, BigDecimal freeTerm) {
        try {
            PlaneValidator.checkCoefficients(coefficientA, coefficientB, coefficientC, freeTerm);
        }catch(Exception ex){
            planeLogger.error(ex);
            throw ex;
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
        int result;
        long temp;
        //I use .doubleValue() so that the scale is not taken into account
        temp = Double.doubleToLongBits(coefficientA.doubleValue());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coefficientB.doubleValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coefficientC.doubleValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(freeTerm.doubleValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
