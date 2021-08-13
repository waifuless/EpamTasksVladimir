package com.epam.jwd.secondtask.service;

/**
 * GCD = greatest common divisor
 */
public class GcdFinder {

    private static GcdFinder instance;

    private GcdFinder() {
    }

    public static GcdFinder getInstance() {
        if (instance == null) {
            instance = new GcdFinder();
        }
        return instance;
    }

    public long findGcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return Math.abs(a);
    }
}
