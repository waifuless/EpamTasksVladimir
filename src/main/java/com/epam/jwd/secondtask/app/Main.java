package com.epam.jwd.secondtask.app;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.services.fileworking.FileExecutorsFactory;
import com.epam.jwd.secondtask.services.fileworking.PlaneByPointsFileFactory;
import com.epam.jwd.secondtask.services.fileworking.PlaneReader;
import org.apache.logging.log4j.*;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class Main {

    public final static Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
        /*
        Point pointA = Point.of(2, 1, 2);
        Point pointB = Point.of(2, 6, 22);
        Point pointC = Point.of(21, -5, 2);

        Plane plane = PlaneExecutor.createPlaneFromThreePoints(pointA, pointB, pointC);

        System.out.println(plane);
        Plane plane = Plane.of(6, 0, 0, 2);

        //System.out.println(PerpendicularityCalculator.isPlanePerpendicularOxz(plane));

        System.out.println(AngleOfPlanesCalculator.calculateAngleBetweenPlanes(plane,
                AngleOfPlanesCalculator.OXY_PLANE));

        Plane plane = Plane.of(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ONE,BigDecimal.TEN);
        Plane plane1 = Plane.of(1,0,1,10);

        System.out.println(""+(plane.equals(plane1))+" "+(plane.hashCode()==plane1.hashCode()));


         */

        FileExecutorsFactory fileExecutorsFactory = new PlaneByPointsFileFactory();
        PlaneReader planeReader = fileExecutorsFactory.makeReader("asd");
    }
}
