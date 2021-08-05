package com.epam.jwd.secondtask.app;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.services.fileworking.FileExecutorsFactory;
import com.epam.jwd.secondtask.services.fileworking.PlaneByPointsFileFactory;
import com.epam.jwd.secondtask.services.fileworking.PlaneReader;
import org.apache.logging.log4j.*;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

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

        String strNum = "1.1 2.2 -5 1 0.52 12 5";



        System.out.println(Pattern.matches("(\\s*[+-]?([0-9]+[.])?[0-9]+\\s*){9}", strNum));



         */


        ApplicationExecutor.findPlanesInFile("coordinates_list/coordinates.txt");
    }
}
