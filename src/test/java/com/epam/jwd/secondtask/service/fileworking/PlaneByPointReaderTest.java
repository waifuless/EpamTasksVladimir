package com.epam.jwd.secondtask.service.fileworking;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.exception.RunOutOfPlanesException;
import com.epam.jwd.secondtask.model.Plane;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PlaneByPointReaderTest {

    private final static File EMPTY_FILE = new File("src/test/resources/test_data/empty.txt");
    private final static File NON_EXISTING_FILE = new File("src/test/resources/test_data/non_exist_space.txt");
    private final static File FILE_WITH_DATA = new File("src/test/resources/test_data/coordinates.txt");


    @Test(expectedExceptions = RunOutOfPlanesException.class)
    public void testNextPlaneEmptyFile() throws IOException {
        try (PlaneByPointReader reader = new PlaneByPointReader(EMPTY_FILE)) {
            reader.nextPlane();
        }
    }

    @Test
    public void testFindAllPlanesEmptyFile() throws IOException {
        try (PlaneByPointReader reader = new PlaneByPointReader(EMPTY_FILE)) {
            List<Plane> list = reader.findAllPlanes();
            Assert.assertTrue(list.isEmpty());
        }
    }

    @Test(expectedExceptions = IOException.class)
    public void testNextPlaneNonExistFile() throws IOException {
        try (PlaneByPointReader reader = new PlaneByPointReader(NON_EXISTING_FILE)) {
            reader.nextPlane();
        }
    }

    @Test(expectedExceptions = IOException.class)
    public void testFindAllPlanesNonExistFile() throws IOException {
        try (PlaneByPointReader reader = new PlaneByPointReader(NON_EXISTING_FILE)) {
            List<Plane> list = reader.findAllPlanes();
        }
    }

    @Test
    public void testNextPlane() throws IOException {
        try (PlaneByPointReader reader = new PlaneByPointReader(FILE_WITH_DATA)) {
            Assert.assertEquals(reader.nextPlane(), Plane.of(4, -18, 23, -82));
        }
    }

    @Test
    public void testFindAllPlanes() throws IOException {
        try (PlaneByPointReader reader = new PlaneByPointReader(FILE_WITH_DATA)) {
            List<Plane> list = reader.findAllPlanes();
            Assert.assertEquals(list.size(), 7);
        }
    }

    @Test
    public void test_FindAllPlanesAndNextPlane_shouldReturnEqualResults() throws IOException {
        try (PlaneByPointReader reader1 = new PlaneByPointReader(FILE_WITH_DATA);
             PlaneByPointReader reader2 = new PlaneByPointReader(FILE_WITH_DATA)) {
            List<Plane> list1 = reader1.findAllPlanes();
            List<Plane> list2 = new ArrayList<>();
            while (reader2.hasNextPlane()) {
                try {
                    list2.add(reader2.nextPlane());
                } catch (Exception ignored) {
                    //do nothing
                }
            }
            Assert.assertEquals(list1, list2);
        }
    }

    @Test(expectedExceptions = ArgumentNullException.class)
    public void testNullArgument() {
        new PlaneByPointReader(null);
    }
}