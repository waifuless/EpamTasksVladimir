package com.epam.jwd.secondtask.services.fileworking;

import com.epam.jwd.secondtask.exceptions.InvalidStringException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.Point;
import com.epam.jwd.secondtask.services.PlaneExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PlaneByPointReader implements PlaneReader {

    //todo: finish regex validator
    //"[+-]?([0-9]+[.])?[0-9]+" for one point
    private final static String VALIDATOR = "([\\s]*[+-]?([0-9]+[.])?[0-9]+[\\s]*){9}";
    private final static int NUMBER_OF_POINTS_IN_PLANE = 3;

    private final File file;
    private Deque<String> stringDeque;
    private List<Plane> planeList;

    public PlaneByPointReader(File file) {
        this.file = file;
        planeList = new ArrayList<>();
    }

    @Override
    public Plane nextPlane() throws IOException {
        fillArrayOfStringsIfItNull();
        return makeNextPlane();
    }

    @Override
    public List<Plane> findAllPlanes() throws IOException {
        fillArrayOfStringsIfItNull();
        while (!stringDeque.isEmpty()) {
            makeNextPlane();
        }
        return planeList;
    }

    @Override
    public File getFile() {
        return file;
    }

    private void fillArrayOfStringsIfItNull() throws IOException {
        if (stringDeque == null) {
            Stream<String> lines = Files.lines(file.toPath());
            //todo: check lines is empty
            stringDeque = lines.collect(ArrayDeque::new, ArrayDeque::add,
                    ArrayDeque::addAll);
        }
    }

    private Plane makeNextPlane() {
        String str = stringDeque.remove();
        if (!Pattern.matches(VALIDATOR, str)) {
            throw new InvalidStringException();
        }
        String[] coordinates = str.replaceAll("\\s{2,}", " ")
                .trim().split("\\s");
        Point[] points = new Point[NUMBER_OF_POINTS_IN_PLANE];
        for (int i = 0, j=0; i < coordinates.length; i+=3, j++) {
            points[j] = Point.of(coordinates[i], coordinates[i + 1], coordinates[i + 2]);
        }
        Plane newPlane = PlaneExecutor.createPlaneFromThreePoints(points[0], points[1], points[2]);
        planeList.add(newPlane);
        return newPlane;
    }
}
