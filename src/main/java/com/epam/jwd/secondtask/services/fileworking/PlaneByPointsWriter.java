package com.epam.jwd.secondtask.services.fileworking;

import com.epam.jwd.secondtask.model.Plane;

import java.io.File;

public class PlaneByPointsWriter implements PlaneWriter {

    private final File file;

    public PlaneByPointsWriter(File file) {
        this.file = file;
    }

    @Override
    public void writePlane(Plane plane) {


    }

    @Override
    public File getFile() {
        return file;
    }
}
