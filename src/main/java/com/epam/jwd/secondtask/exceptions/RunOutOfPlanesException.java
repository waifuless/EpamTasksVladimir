package com.epam.jwd.secondtask.exceptions;

public class RunOutOfPlanesException extends RuntimeException{

    public RunOutOfPlanesException(){

    }

    public RunOutOfPlanesException(ExceptionMessages mcg){
        super(mcg.getMessage());
    }
}
