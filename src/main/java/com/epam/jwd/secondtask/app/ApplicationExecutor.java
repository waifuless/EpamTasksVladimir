package com.epam.jwd.secondtask.app;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.services.calculation.AngleOfPlanesCalculator;
import com.epam.jwd.secondtask.services.fileworking.FileExecutorsFactory;
import com.epam.jwd.secondtask.services.fileworking.PlaneReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.jwd.secondtask.services.calculation.PerpendicularityCalculator;

import java.io.IOException;
import java.util.function.Function;

public class ApplicationExecutor {

    private final static Function<Plane, Boolean> COMMON_PERPENDICULAR_FUNCTION
            = PerpendicularityCalculator::isPlanePerpendicularOxz;
    private final static String COMMON_PERPENDICULAR_INFO_MCG = "\tPlane perpendicular with OXZ: %b";
    private final static Plane COMMON_PLANE_FOR_ANGLE_CALCULATE = AngleOfPlanesCalculator.OXY_PLANE;
    private final static String COMMON_ANGLE_INFO_MCG = "\tPlane angle with OXY: %s";

    private final static String PLANE_WAS_NOT_CREATED_MCG = "The plane was not created";
    private final static Logger appLogger = LogManager.getLogger(ApplicationExecutor.class);

    public static void findPlanesInFile(String filePath) {

        //funcInterface funcInterface = c -> c.isPlanePerpendicularOxz(new Plane);
        appLogger.trace("Program start");
        try {
            FileExecutorsFactory executorsFactory = FileExecutorsFactory.create();
            PlaneReader planeReader = executorsFactory.makeReader(filePath);
            Plane plane;
            while (planeReader.hesNextPlane()) {
                try {
                    plane = planeReader.nextPlane();
                    appLogger.info(plane);
                    appLogger.info(String.format(COMMON_PERPENDICULAR_INFO_MCG,
                            COMMON_PERPENDICULAR_FUNCTION.apply(plane)));
                    appLogger.info(String.format(COMMON_ANGLE_INFO_MCG,
                            AngleOfPlanesCalculator
                                    .calculateAngleBetweenPlanes(plane,COMMON_PLANE_FOR_ANGLE_CALCULATE)));
                }catch (Exception ex) {
                    appLogger.warn(PLANE_WAS_NOT_CREATED_MCG);
                }
            }
        }catch (IOException ex){
            appLogger.error(ex);
        }
        appLogger.trace("Program end");
    }
}
