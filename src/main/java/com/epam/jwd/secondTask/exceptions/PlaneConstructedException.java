package com.epam.jwd.secondTask.exceptions;

public class PlaneConstructedException extends RuntimeException{

    public PlaneConstructedException(){

    }

    public PlaneConstructedException(String mcg){
        super(mcg);
    }

    public PlaneConstructedException(ExceptionMessages mcg){
        super(mcg.getMessage());
    }

}
