package com.epam.jwd.secondtask.service.fileworking;

import com.epam.jwd.secondtask.model.Plane;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PlaneReader extends AutoCloseable{

    Plane nextPlane() throws IOException;
    boolean hasNextPlane() throws IOException;
    List<Plane> findAllPlanes() throws IOException;
    File getFile();

}
