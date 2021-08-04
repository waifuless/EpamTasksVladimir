package com.epam.jwd.secondtask.services.fileworking;

import java.io.File;

public class PlaneByPointsFileFactory implements FileExecutorsFactory {

    @Override
    public PlaneReader makeReader(File file){
        return new PlaneByPointReader(file);
    }

    @Override
    public PlaneReader makeReader(String filePath){
        return new PlaneByPointReader(new File(filePath));
    }

    @Override
    public PlaneWriter makeWriter(File file){
        return new PlaneByPointsWriter(file);
    }

    @Override
    public PlaneWriter makeWriter(String filePath){
        return new PlaneByPointsWriter(new File(filePath));
    }
}
