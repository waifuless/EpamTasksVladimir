package com.epam.jwd.secondtask.app;

import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.testng.Assert.*;

public class ApplicationExecutorTest {

    private final static String UN_EXISTING_FILE_PATH = "coordinates_list/unExistingFile.txt";

    @Test(expectedExceptions = IOException.class)
    public void testFindPlanesInFile() throws IOException{
       ApplicationExecutor.findPlanesInFile(UN_EXISTING_FILE_PATH);
    }
}