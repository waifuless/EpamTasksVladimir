package com.epam.jwd.secondtask.model;

import com.epam.jwd.secondtask.exceptions.ExceptionMessages;
import com.epam.jwd.secondtask.exceptions.PointConstructedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Point {

    //Coordinates
    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal z;

    private final static Logger pointLogger = LogManager.getLogger(Point.class);

    Point(BigDecimal x, BigDecimal y, BigDecimal z) {
        if (x == null || y == null || z == null) {
            PointConstructedException ex = new PointConstructedException(ExceptionMessages.ARGUMENT_IS_NULL_MCG);
            pointLogger.error(ex);
            throw ex;
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point of(double x, double y, double z) {
        return new Point(BigDecimal.valueOf(x),
                BigDecimal.valueOf(y), BigDecimal.valueOf(z));
    }

    public static Point of(String x, String y, String z) {
        return new Point(new BigDecimal(x), new BigDecimal(y), new BigDecimal(z));
    }

    public static Point of(BigDecimal x, BigDecimal y, BigDecimal z) {
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

        if (x.compareTo(point.x)!=0) return false;
        if (y.compareTo(point.y)!=0) return false;
        return z.compareTo(point.z)==0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        //I use .doubleValue() so that the scale is not taken into account
        temp = Double.doubleToLongBits(x.doubleValue());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y.doubleValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z.doubleValue());
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
