package com.epam.jwd.secondtask.services.calculation;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.services.PlaneValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AngleOfPlanesCalculator {

    public final static Plane OXY_PLANE = Plane.of(0, 0, 1, 0);
    public final static Plane OXZ_PLANE = Plane.of(0, 1, 0, 0);
    public final static Plane OYZ_PLANE = Plane.of(1, 0, 0, 0);
    private final static int COMMON_DIVIDE_SCALE = 8;

    private final static Logger angleLogger = LogManager.getLogger(AngleOfPlanesCalculator.class);

    public static BigDecimal calculateAngleBetweenPlanes(Plane planeA, Plane planeB) {
        try {
            PlaneValidator.checkPlane(planeA);
            PlaneValidator.checkPlane(planeB);
        }catch (Exception ex){
            angleLogger.error(ex);
            throw ex;
        }
        //Formula(A, B, C - coefficients): cos α = (|A1·A2 + B1·B2 + C1·C2|)/(√(A1^2 + B1^2 + C1^2)*(A2^2 + B2^2 + C2^2))
        BigDecimal numerator = planeA.getCoefficientA().multiply(planeB.getCoefficientA())
                .add(planeA.getCoefficientB().multiply(planeB.getCoefficientB()))
                .add(planeA.getCoefficientC().multiply(planeB.getCoefficientC()))
                .abs();
        BigDecimal denominator = BigDecimal.valueOf(Math.sqrt(
                ((planeA.getCoefficientA().pow(2).add(planeA.getCoefficientB().pow(2))
                        .add(planeA.getCoefficientC().pow(2)))
                        .multiply(planeB.getCoefficientA().pow(2).add(planeB.getCoefficientB().pow(2))
                                .add(planeB.getCoefficientC().pow(2)))).doubleValue()));

        BigDecimal cosOfAngle = numerator.divide(denominator, COMMON_DIVIDE_SCALE, RoundingMode.HALF_UP);
        return BigDecimal.valueOf(Math.toDegrees(Math.acos(cosOfAngle.doubleValue())));
    }
}
