package com.epam.jwd.secondtask.services.fileworking;

import com.epam.jwd.secondtask.model.Plane;

import java.io.File;

public interface PlaneReader {

    Plane readPlane();
    File getFile();

}
