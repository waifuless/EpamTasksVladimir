package com.epam.jwd.secondtask.app;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class ApplicationExecutorTest {

    private final static File UN_EXISTING_FILE_PATH = new File("coordinates_list/unExistingFile.txt");
    private final static File FILE_WITH_DATA = new File("test_data/coordinates.txt");
    private final static File DIRECTORIES = new File("test_data/");


    @BeforeTest
    private void initFiles() throws IOException {
        Files.deleteIfExists(FILE_WITH_DATA.toPath());

        Files.createDirectories(DIRECTORIES.toPath());
        Files.createFile(FILE_WITH_DATA.toPath());

        String[] strings = new String[]{
                "  2 1 4 6   7 8 9 0 2",
                "1.1 2.2 -5 1 0.52 12 3 2",
                "1.1 5 0 2 1 0 3 -1 0",
                "1 2 0 2 1 0 4 -2 0",
                "0 0 0 0 0 0 0 0 0",
                "  1 1 1 1 1 1 1 1 1",
                "1.2   1.101 -2.1 +3 0 2222.21123   5 2 1",
                "  1   2 3 3 5 3 7 2 1",
                "",
                "",
                "  2 1 4 6   7 8 9 0 2",
                "",
                "    12 dasd 1223 123",
                "",
                "  1 0 2 3 4 2 1 5 6.2"
        };
        Files.write(FILE_WITH_DATA.toPath(), Arrays.asList(strings));
    }


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