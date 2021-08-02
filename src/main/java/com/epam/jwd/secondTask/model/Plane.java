package com.epam.jwd.secondTask.model;

public class Plane {

    //The coefficients of the equation of the plane
    private final double coefficientA;
    private final double coefficientB;
    private final double coefficientC;
    private final double freeTerm;

    private Plane(double coefficientA, double coefficientB, double coefficientC, double freeTerm) {
        this.coefficientA = coefficientA;
        this.coefficientB = coefficientB;
        this.coefficientC = coefficientC;
        this.freeTerm = freeTerm;
    }

    public static Plane of(double coefficientA, double coefficientB, double coefficientC, double freeTerm){
        return new Plane(coefficientA, coefficientB, coefficientC, freeTerm);
    }

    public static Plane of(String coefficientA, String coefficientB, String coefficientC, String freeTerm){
        return new Plane(Double.parseDouble(coefficientA), Double.parseDouble(coefficientB),
                Double.parseDouble(coefficientC), Double.parseDouble(freeTerm));
    }

    public double getCoefficientA() {
        return coefficientA;
    }

    public double getCoefficientB() {
        return coefficientB;
    }

    public double getCoefficientC() {
        return coefficientC;
    }

    public double getFreeTerm() {
        return freeTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (Double.compare(plane.coefficientA, coefficientA) != 0) return false;
        if (Double.compare(plane.coefficientB, coefficientB) != 0) return false;
        if (Double.compare(plane.coefficientC, coefficientC) != 0) return false;
        return Double.compare(plane.freeTerm, freeTerm) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(coefficientA);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coefficientB);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coefficientC);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(freeTerm);
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
