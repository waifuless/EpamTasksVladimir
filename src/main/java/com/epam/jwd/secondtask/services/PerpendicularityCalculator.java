package com.epam.jwd.secondtask.services;

import com.epam.jwd.secondtask.model.Plane;

import java.math.BigDecimal;


public class PerpendicularityCalculator {

    private static boolean isPlaneValid(Plane plane){
        if(plane.getCoefficientA()==null||plane.getCoefficientB()==null||plane.getCoefficientC()==null){
            //todo: Send exception?
            return false;
        }
        if(plane.getCoefficientA().compareTo(plane.getCoefficientB())==0
                &&plane.getCoefficientA().compareTo(plane.getCoefficientC())==0
                &&plane.getCoefficientA().compareTo(BigDecimal.ZERO)==0){
            return false;
        }
        return true;
    }

    public static boolean isPlanePerpendicularOxy(Plane plane){
        if(!isPlaneValid(plane)){
            //todo: Error
            throw new RuntimeException();
        }
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
        if(!isPlaneValid(plane)){
            //todo: Error
            throw new RuntimeException();
        }
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
        if(!isPlaneValid(plane)){
            //todo: Error
            throw new RuntimeException();
        }
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
