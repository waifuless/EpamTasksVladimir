package com.epam.jwd.secondTask.services;

import com.epam.jwd.secondTask.model.Plane;
import com.epam.jwd.secondTask.model.Point;

import java.math.BigDecimal;

public class PlaneExecutor {

    public static Plane createPlaneFromThreePoints(Point firstP, Point secondP, Point thirdP){
        if(!arePointsValidToMakePlane(firstP, secondP, thirdP)){
            //Error
            throw new RuntimeException();
        }
        
        //Calculate from formula, I made on paper
        BigDecimal coefficientA = (secondP.getY().subtract(firstP.getY()))
                .multiply(thirdP.getZ().subtract(firstP.getZ())).subtract
                        ((thirdP.getY().subtract(firstP.getY())).multiply(secondP.getZ().subtract(firstP.getZ())));
        BigDecimal coefficientB = (secondP.getX().subtract(firstP.getX()))
                .multiply(thirdP.getZ().subtract(firstP.getZ())).subtract
                        ((thirdP.getX().subtract(firstP.getX())).multiply(secondP.getZ().subtract(firstP.getZ())));
        BigDecimal coefficientC = (secondP.getX().subtract(firstP.getX()))
                .multiply(thirdP.getY().subtract(firstP.getY())).subtract
                        ((thirdP.getX().subtract(firstP.getX())).multiply(secondP.getY().subtract(firstP.getY())));
        BigDecimal freeTerm = firstP.getX().negate().multiply(coefficientA).add
                (firstP.getY().multiply(coefficientB)).subtract(firstP.getZ().multiply(coefficientC));

        //Second coefficient with minus, by my formula I made on paper
        return Plane.of(coefficientA, coefficientB.negate(), coefficientC, freeTerm);
    }

    public static boolean arePointsValidToMakePlane(Point firstP, Point secondP, Point thirdP){
        if(firstP==null || secondP==null || thirdP==null){
            return false;
        }

        //Check that points are not equals
        if(firstP.equals(secondP)||firstP.equals(thirdP)||secondP.equals(thirdP)){
            return false;
        }

        //Check that the points are not on the same line
        if(firstP.getX().equals(secondP.getX())&&
                firstP.getX().equals(thirdP.getX())){
            return false;
        }
        if(firstP.getY().equals(secondP.getY())&&
                firstP.getY().equals(thirdP.getY())){
            return false;
        }
        if(firstP.getZ().equals(secondP.getZ())&&
                firstP.getZ().equals(thirdP.getZ())){
            return false;
        }

        return true;
    }
}
