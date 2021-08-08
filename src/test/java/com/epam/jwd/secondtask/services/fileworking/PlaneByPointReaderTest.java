package com.epam.jwd.secondtask.services.fileworking;

import com.epam.jwd.secondtask.exceptions.RunOutOfPlanesException;
import com.epam.jwd.secondtask.model.Plane;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class PlaneByPointReaderTest {

    private final static Path DIRECTORIES = Paths.get("test_data/");
    private final static File EMPTY_FILE = new File("test_data/empty.txt");
    private final static File NON_EXISTING_FILE = new File("test_data/non_exist_space.txt");
    private final static File FILE_WITH_DATA = new File("test_data/coordinates.txt");

    @BeforeTest
    private void initFiles() throws IOException {
        Files.deleteIfExists(NON_EXISTING_FILE.toPath());
        Files.deleteIfExists(EMPTY_FILE.toPath());
        Files.deleteIfExists(FILE_WITH_DATA.toPath());

        Files.createDirectories(DIRECTORIES);
        Files.createFile(EMPTY_FILE.toPath());
        Files.createFile(FILE_WITH_DATA.toPath());

        String [] strings = new String[]{
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

    @Test(expectedExceptions = RunOutOfPlanesException.class)
    public void testNextPlaneEmptyFile() throws IOException {
        PlaneByPointReader reader = new PlaneByPointReader(EMPTY_FILE);
        reader.nextPlane();
    }

    @Test
    public void testFindAllPlanesEmptyFile() throws IOException {
        PlaneByPointReader reader = new PlaneByPointReader(EMPTY_FILE);
        List<Plane> list = reader.findAllPlanes();
        Assert.assertTrue(list.isEmpty());
    }

    @Test(expectedExceptions = IOException.class)
    public void testNextPlaneNonExistFile() throws IOException {
        PlaneByPointReader reader = new PlaneByPointReader(NON_EXISTING_FILE);
        reader.nextPlane();
    }

    @Test(expectedExceptions = IOException.class)
    public void testFindAllPlanesNonExistFile() throws IOException {
        PlaneByPointReader reader = new PlaneByPointReader(NON_EXISTING_FILE);
        List<Plane> list = reader.findAllPlanes();
    }

    @Test
    public void testNextPlane() throws IOException {
        PlaneByPointReader reader = new PlaneByPointReader(FILE_WITH_DATA);
        Assert.assertEquals(reader.nextPlane(), Plane.of(4, -18, 23, -82));
    }

    @Test
    public void testFindAllPlanes() throws IOException {
        PlaneByPointReader reader = new PlaneByPointReader(FILE_WITH_DATA);
        List<Plane> list = reader.findAllPlanes();
        Assert.assertEquals(list.size(), 7);
    }
}