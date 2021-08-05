package com.epam.jwd.secondtask.services.calculation;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.services.PlaneValidator;

import java.math.BigDecimal;


public class PerpendicularityCalculator {

    public static boolean isPlanePerpendicularOxy(Plane plane){
        PlaneValidator.checkPlane(plane);
        if(plane.getCoefficientB().compareTo(plane.getCoefficientC())==0
                &&plane.getCoefficientB().compareTo(BigDecimal.ZERO)==0){
            return true;
        }
        if(plane.getCoefficientA().compareTo(plane.getCoefficientC())==0
                &&plane.getCoefficientA().compareTo(BigDecimal.ZERO)==0){
            return true;
        }
        return false;
    }

    public static boolean isPlanePerpendicularOxz(Plane plane){
        PlaneValidator.checkPlane(plane);
        if(plane.getCoefficientB().compareTo(plane.getCoefficientA())==0
                &&plane.getCoefficientB().compareTo(BigDecimal.ZERO)==0){
            return true;
        }
        if(plane.getCoefficientB().compareTo(plane.getCoefficientC())==0
                &&plane.getCoefficientB().compareTo(BigDecimal.ZERO)==0){
            return true;
        }
        return false;
    }

    public static boolean isPlanePerpendicularOyz(Plane plane){
        PlaneValidator.checkPlane(plane);
        if(plane.getCoefficientB().compareTo(plane.getCoefficientA())==0
                &&plane.getCoefficientB().compareTo(BigDecimal.ZERO)==0){
            return true;
        }
        if(plane.getCoefficientA().compareTo(plane.getCoefficientC())==0
                &&plane.getCoefficientA().compareTo(BigDecimal.ZERO)==0){
            return true;
        }
        return false;
    }

}
