package com.epam.jwd.secondtask.services.fileworking;

import com.epam.jwd.secondtask.model.Plane;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PlaneReader {

    Plane nextPlane() throws IOException;
    boolean hasNextPlane() throws IOException;
    List<Plane> findAllPlanes() throws IOException;
    File getFile();

}
