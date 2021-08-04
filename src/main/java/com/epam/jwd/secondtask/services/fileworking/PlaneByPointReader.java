package com.epam.jwd.secondtask.services.fileworking;

import com.epam.jwd.secondtask.model.Plane;

import java.io.File;

public class PlaneByPointReader implements PlaneReader {

    private final File file;

    public PlaneByPointReader(File file) {
        this.file = file;
    }

    @Override
    public Plane readPlane() {
        return null;
    }

    @Override
    public File getFile() {
        return file;
    }
}
