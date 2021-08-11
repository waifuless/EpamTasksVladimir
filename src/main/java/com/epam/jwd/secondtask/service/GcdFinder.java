package com.epam.jwd.secondtask.service;

//GCD = greatest common divisor
public class GcdFinder {
    public static long findGcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return Math.abs(a);
    }
}
