package com.epam.jwd.secondtask.services.fileworking;

import java.io.File;

public interface FileExecutorsFactory {

    PlaneReader makeReader(File file);
    PlaneReader makeReader(String filePath);

    static FileExecutorsFactory create(){
        return new PlaneByPointsFileFactory();
    }

}
