package com.epam.jwd.secondtask.service.fileworking;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.exception.ExceptionMessages;
import com.epam.jwd.secondtask.exception.InvalidStringException;
import com.epam.jwd.secondtask.exception.RunOutOfPlanesException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.Point;
import com.epam.jwd.secondtask.service.PlaneExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;


//Every nextPlane() delete string from top of stringDeque and if string is correct, add Plane to the end of planeList
public class PlaneByPointReader implements PlaneReader {

    private final static Logger LOG = LogManager.getLogger(PlaneByPointReader.class);

    //"[+-]?([0-9]+[.])?[0-9]+" for one point
    private final static String VALIDATOR = "[+-]?([0-9]+[.])?[0-9]+";
    private final static int NUMBER_OF_POINTS_IN_PLANE = 3;
    private final static int NUMBER_OF_ALL_COORDINATES = 9;

    private final File file;
    private final List<Plane> planeList;
    private Deque<String> stringDeque;

    PlaneByPointReader(File file) {
        if (file == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
        this.file = file;
        planeList = new ArrayList<>();
    }

    @Override
    public Plane nextPlane() throws IOException {
        fillArrayOfStringsIfItNull();
        if (stringDeque.isEmpty()) {
            throw new RunOutOfPlanesException(ExceptionMessages.RUN_OUT_OF_PLANES_MCG);
        }
        return makeNextPlane();
    }

    @Override
    public boolean hasNextPlane() throws IOException {
        fillArrayOfStringsIfItNull();
        return !stringDeque.isEmpty();
    }

    @Override
    public List<Plane> findAllPlanes() throws IOException {
        fillArrayOfStringsIfItNull();
        while (!stringDeque.isEmpty()) {
            try {
                makeNextPlane();
            } catch (Exception ignored) {//Invalid strings just will be skipped
                //ignore
            }
        }
        return planeList;
    }

    @Override
    public File getFile() {
        return file;
    }


    //just free memory
    @Override
    public void close() {
        if (stringDeque != null) {
            stringDeque.clear();
        }
        planeList.clear();
    }

    private void fillArrayOfStringsIfItNull() throws IOException {
        if (stringDeque == null) {
            Stream<String> lines = Files.lines(file.toPath());
            stringDeque = lines.filter(str -> !str.trim().equals("")).collect(ArrayDeque::new, ArrayDeque::add,
                    ArrayDeque::addAll);
        }
    }

    private Plane makeNextPlane() {
        String str = stringDeque.remove();
        String[] coordinates = str.trim().split("\\s+");
        if (coordinates.length != NUMBER_OF_ALL_COORDINATES) {
            InvalidStringException ex = new InvalidStringException(str);
            LOG.error(ex);
            throw ex;
        }
        for (int i = 0; i < coordinates.length; i++) {
            if (!Pattern.matches(VALIDATOR, coordinates[i])) {
                InvalidStringException ex = new InvalidStringException(str);
                LOG.error(ex);
                throw ex;
            }
        }
        Point[] points = new Point[NUMBER_OF_POINTS_IN_PLANE];
        for (int i = 0, j = 0; i < coordinates.length; i += 3, j++) {
            points[j] = Point.of(coordinates[i], coordinates[i + 1], coordinates[i + 2]);
        }
        Plane newPlane = PlaneExecutor.createPlaneFromThreePoints(points[0], points[1], points[2]);
        planeList.add(newPlane);
        return newPlane;
    }
}
