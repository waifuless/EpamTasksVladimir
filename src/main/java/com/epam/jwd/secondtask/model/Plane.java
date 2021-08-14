package com.epam.jwd.secondtask.model;

import com.epam.jwd.secondtask.exception.ExceptionMessages;
import com.epam.jwd.secondtask.exception.PlaneConstructedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;

//todo: fully remake plane to mutable and make it observable
public class Plane {

    private final static Logger LOG = LogManager.getLogger(Plane.class);
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    /**
     * The coefficients of the equation of the plane
     */
    private BigDecimal coefficientA;
    private BigDecimal coefficientB;
    private BigDecimal coefficientC;
    private BigDecimal freeTerm;

    Plane(BigDecimal coefficientA, BigDecimal coefficientB, BigDecimal coefficientC, BigDecimal freeTerm) {
        try {
            checkCoefficients(coefficientA, coefficientB, coefficientC, freeTerm);
        } catch (Exception ex) {
            LOG.error(ex);
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

    public void setCoefficientA(BigDecimal coefficientA) {
        BigDecimal oldValue = this.coefficientA;
        this.coefficientA = coefficientA;
        changes.firePropertyChange("coefficientA", oldValue, coefficientA);
    }

    public BigDecimal getCoefficientB() {
        return coefficientB;
    }

    public void setCoefficientB(BigDecimal coefficientB) {
        BigDecimal oldValue = this.coefficientB;
        this.coefficientB = coefficientB;
        changes.firePropertyChange("coefficientB", oldValue, coefficientB);
    }

    public BigDecimal getCoefficientC() {
        return coefficientC;
    }

    public void setCoefficientC(BigDecimal coefficientC) {
        BigDecimal oldValue = this.coefficientC;
        this.coefficientC = coefficientC;
        changes.firePropertyChange("coefficientC", oldValue, coefficientC);
    }

    public BigDecimal getFreeTerm() {
        return freeTerm;
    }

    public void setFreeTerm(BigDecimal freeTerm) {
        BigDecimal oldValue = this.freeTerm;
        this.freeTerm = freeTerm;
        changes.firePropertyChange("freeTerm", oldValue, freeTerm);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (coefficientA.compareTo(plane.coefficientA) != 0) return false;
        if (coefficientB.compareTo(plane.coefficientB) != 0) return false;
        if (coefficientC.compareTo(plane.coefficientC) != 0) return false;
        return freeTerm.compareTo(plane.freeTerm) == 0;
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

    private void checkCoefficients(BigDecimal coefficientA, BigDecimal coefficientB,
                                   BigDecimal coefficientC, BigDecimal freeTerm) {
        if (coefficientA == null || coefficientB == null || coefficientC == null || freeTerm == null) {
            LOG.error(ExceptionMessages.ARGUMENT_IS_NULL_MCG.getMessage());
            throw new PlaneConstructedException(ExceptionMessages.ARGUMENT_IS_NULL_MCG);
        }
        if (coefficientA.compareTo(coefficientB) == 0 && coefficientA.compareTo(coefficientC) == 0
                && coefficientA.compareTo(BigDecimal.ZERO) == 0) {
            LOG.error(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG.getMessage());
            throw new PlaneConstructedException(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG);
        }
    }
}
