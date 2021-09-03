package com.epam.jwd.thirdtask.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileToOneStringReader {

    private static volatile FileToOneStringReader instance;

    private FileToOneStringReader(){}

    public static FileToOneStringReader getInstance() {
        if(instance==null){
            synchronized (FileToOneStringReader.class){
                if(instance==null){
                    instance = new FileToOneStringReader();
                }
            }
        }
        return instance;
    }

    public String readToOneString(String filePath) throws IOException{
        StringBuilder contentBuilder = new StringBuilder();
        Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                .forEachOrdered(s -> contentBuilder.append(s).append("\n"));
        return contentBuilder.toString();
    }
}
