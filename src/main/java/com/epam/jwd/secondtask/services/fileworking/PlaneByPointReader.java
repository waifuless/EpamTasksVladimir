package com.epam.jwd.secondtask.services.fileworking;

import com.epam.jwd.secondtask.exceptions.ExceptionMessages;
import com.epam.jwd.secondtask.exceptions.InvalidStringException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.Point;
import com.epam.jwd.secondtask.services.PlaneExecutor;
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

    private final static Logger readerLogger = LogManager.getLogger(PlaneByPointReader.class);

    //"[+-]?([0-9]+[.])?[0-9]+" for one point
    private final static String VALIDATOR = "([\\s]*[+-]?([0-9]+[.])?[0-9]+[\\s]*){9}";
    private final static int NUMBER_OF_POINTS_IN_PLANE = 3;
    private final static int NUMBER_OF_ALL_COORDINATES = 9;

    private final File file;
    private Deque<String> stringDeque;
    private List<Plane> planeList;

    PlaneByPointReader(File file) {
        this.file = file;
        planeList = new ArrayList<>();
    }

    @Override
    public Plane nextPlane() throws IOException {
        fillArrayOfStringsIfItNull();
        return makeNextPlane();
    }

    @Override
    public boolean hesNextPlane() throws IOException{
        fillArrayOfStringsIfItNull();
        return !stringDeque.isEmpty();
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
            stringDeque = lines.filter(str -> !str.trim().equals("")).collect(ArrayDeque::new, ArrayDeque::add,
                    ArrayDeque::addAll);
        }
    }

    private Plane makeNextPlane() {
        String str = stringDeque.remove();
        if (!Pattern.matches(VALIDATOR, str)) {
            InvalidStringException ex = new InvalidStringException(str);
            readerLogger.error(ex);
            throw ex;
        }
        String[] coordinates = str.replaceAll("\\s{2,}", " ")
                .trim().split("\\s");
        if(coordinates.length!=NUMBER_OF_ALL_COORDINATES){
            InvalidStringException ex = new InvalidStringException(ExceptionMessages.PARSING_ERROR_MCG);
            readerLogger.error(ex);
            throw ex;
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
