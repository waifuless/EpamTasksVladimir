package com.epam.jwd.secondTask.model;

import java.math.BigDecimal;

public class Point {

    //Coordinates
    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal z;

    private Point(BigDecimal x, BigDecimal y, BigDecimal z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point of(double x, double y, double z){
        return new Point(BigDecimal.valueOf(x),
                BigDecimal.valueOf(y), BigDecimal.valueOf(z));
    }

    public static Point of(String x, String y, String z){
        return new Point(new BigDecimal(x), new BigDecimal(y), new BigDecimal(z));
    }

    public static Point of(BigDecimal x, BigDecimal y, BigDecimal z){
        return new Point(x, y, z);
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public BigDecimal getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (!x.equals(point.x)) return false;
        if (!y.equals(point.y)) return false;
        return z.equals(point.z);
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        result = 31 * result + z.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
