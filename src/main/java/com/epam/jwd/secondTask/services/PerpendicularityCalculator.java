package com.epam.jwd.secondTask.services;

import com.epam.jwd.secondTask.model.Point;
import com.epam.jwd.secondTask.model.Plane;


public class PerpendicularityCalculator {

    private static boolean isPlaneValid(Plane plane){
        if(plane.getCoefficientA()==null||plane.getCoefficientB()==null||plane.getCoefficientC()==null){
            //todo: Send exception?
            return false;
        }
        if(plane.getCoefficientA().equals(plane.getCoefficientB())
                &&plane.getCoefficientA().equals(plane.getCoefficientC())
                &&plane.getCoefficientA().doubleValue()==0.0){
            return false;
        }
        return true;
    }

    public static boolean isPlanePerpendicularOxy(Plane plane){
        if(!isPlaneValid(plane)){
            //todo: Error
            throw new RuntimeException();
        }
        if(plane.getCoefficientB().equals(plane.getCoefficientC())&&plane.getCoefficientB().doubleValue()==0.0){
            return true;
        }
        if(plane.getCoefficientA().equals(plane.getCoefficientC())&&plane.getCoefficientA().doubleValue()==0.0){
            return true;
        }
        return false;
    }

    public static boolean isPlanePerpendicularOxz(Plane plane){
        if(!isPlaneValid(plane)){
            //todo: Error
            throw new RuntimeException();
        }
        if(plane.getCoefficientB().equals(plane.getCoefficientA())&&plane.getCoefficientB().doubleValue()==0.0){
            return true;
        }
        if(plane.getCoefficientB().equals(plane.getCoefficientC())&&plane.getCoefficientB().doubleValue()==0.0){
            return true;
        }
        return false;
    }

    public static boolean isPlanePerpendicularOyz(Plane plane){
        if(!isPlaneValid(plane)){
            //todo: Error
            throw new RuntimeException();
        }
        if(plane.getCoefficientB().equals(plane.getCoefficientA())&&plane.getCoefficientB().doubleValue()==0.0){
            return true;
        }
        if(plane.getCoefficientA().equals(plane.getCoefficientC())&&plane.getCoefficientA().doubleValue()==0.0){
            return true;
        }
        return false;
    }

}
