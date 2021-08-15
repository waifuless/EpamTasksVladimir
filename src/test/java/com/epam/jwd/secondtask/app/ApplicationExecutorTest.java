package com.epam.jwd.secondtask.app;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class ApplicationExecutorTest {

    private final static File UN_EXISTING_FILE_PATH = new File("src/test/resources/test_data/unExistingFile.txt");
    private final static File FILE_WITH_DATA = new File("src/test/resources/test_data/coordinates.txt");



    @Test(expectedExceptions = IOException.class)
    public void testFindPlanesInFile() throws IOException {
        ApplicationExecutor.findPlanesInFile(UN_EXISTING_FILE_PATH.getPath());
    }

    @Test
    public void testNotException() throws IOException {
        ApplicationExecutor.findPlanesInFile(FILE_WITH_DATA.getPath());
    }

    @Test(expectedExceptions = ArgumentNullException.class)
    public void testNullArgument() throws IOException {
        ApplicationExecutor.findPlanesInFile(null);
    }
}