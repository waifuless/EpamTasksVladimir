package com.epam.jwd.app;

import com.epam.jwd.model.Point;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger mainLogger = LoggerFactory.getLogger(Main.class);
    private static final int DEFAULT_ARRAY_SIZE = 5;

    public static void main(String [] args){
        try {
            mainLogger.trace("Program started");
            int arrSize;

            if (args.length > 0) {
                arrSize = Integer.parseInt(args[0]);
            }
            else{
                mainLogger.warn("No arguments were received. Default size to array = "+DEFAULT_ARRAY_SIZE);
                arrSize = DEFAULT_ARRAY_SIZE;
            }

            Point[] arr = new Point[arrSize];
            mainLogger.info("Array has created with length = "+arr.length);
            Random random = new Random();

            for (Point point : arr) {
                point = new Point(random.nextDouble() * 10 - 5, random.nextDouble() * 10 - 5);
                mainLogger.info(point.toString());
            }

        }catch (Exception ex){
            mainLogger.error("Exception", ex);
            mainLogger.trace("Program end working with fatal error");
            System.exit(-1);
        }
        mainLogger.trace("Program end working without errors");
    }
}
