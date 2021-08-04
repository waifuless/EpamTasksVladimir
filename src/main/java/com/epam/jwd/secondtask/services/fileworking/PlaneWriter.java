package com.epam.jwd.secondtask.services.fileworking;

import com.epam.jwd.secondtask.model.Plane;

import java.io.File;

public interface PlaneWriter {

    void writePlane(Plane plane);
    File getFile();
}
