package com.epam.jwd.secondtask.services;

import com.epam.jwd.secondtask.model.Plane;

import java.math.BigDecimal;

public class PlaneValidator {

    //todo: static methods to validate plane

    public static void checkPlane(Plane plane){
        if(plane.getCoefficientA()==null||plane.getCoefficientB()==null||plane.getCoefficientC()==null){
            //todo: Send exception?
        }
        if(plane.getCoefficientA().compareTo(plane.getCoefficientB())==0
                &&plane.getCoefficientA().compareTo(plane.getCoefficientC())==0
                &&plane.getCoefficientA().compareTo(BigDecimal.ZERO)==0){
            //todo: send exc
        }
    }
}
