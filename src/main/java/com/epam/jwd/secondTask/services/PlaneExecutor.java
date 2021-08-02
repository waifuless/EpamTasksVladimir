package com.epam.jwd.secondTask.services;

import com.epam.jwd.secondTask.model.Plane;
import com.epam.jwd.secondTask.model.Point;

public class PlaneExecutor {

    public static Plane createPlaneFromThreePoints(Point firstP, Point secondP, Point thirdP){
        if(!arePointsValidToMakePlane(firstP, secondP, thirdP)){
            //Error
            throw new RuntimeException();
        }

        //Calculate from formula, I made on paper
        double coefficientA = (secondP.getY()-firstP.getY())*(thirdP.getZ()-firstP.getZ())
                -(thirdP.getY()- firstP.getY())*(secondP.getZ()-firstP.getZ());
        double coefficientB = (secondP.getX()-firstP.getX())*(thirdP.getZ()-firstP.getZ())
                -(thirdP.getX()- firstP.getX())*(secondP.getZ()-firstP.getZ());
        double coefficientC = (secondP.getX()-firstP.getX())*(thirdP.getY()-firstP.getY())
                -(thirdP.getX()- firstP.getX())*(secondP.getY()-firstP.getY());
        double freeTerm = -firstP.getX()*coefficientA+firstP.getY()*coefficientB-firstP.getZ()*coefficientC;

        //Second coefficient with minus, by my formula I made on paper
        return Plane.of(coefficientA, -coefficientB, coefficientC, freeTerm);
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
        if(firstP.getX()==secondP.getX()&&
                firstP.getX()==thirdP.getX()){
            return false;
        }
        if(firstP.getY()==secondP.getY()&&
                firstP.getY()==thirdP.getY()){
            return false;
        }
        if(firstP.getZ()==secondP.getZ()&&
                firstP.getZ()==thirdP.getZ()){
            return false;
        }
        return true;
    }
}
