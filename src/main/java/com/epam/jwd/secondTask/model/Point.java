package com.epam.jwd.secondTask.model;

public class Point {

    //Coordinates
    private final double x;
    private final double y;
    private final double z;

    private Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point of(double x, double y, double z){
        return new Point(x, y, z);
    }

    public static Point of(String x, String y, String z){
        return new Point(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        if (Double.compare(point.y, y) != 0) return false;
        return Double.compare(point.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
