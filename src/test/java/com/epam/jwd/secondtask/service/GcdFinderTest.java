package com.epam.jwd.secondtask.service;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GcdFinderTest {

    private final GcdFinder gcdFinder = GcdFinder.getInstance();

    @DataProvider
    public Object[][] findGcdData() {
        return new Object[][]{
                {1, -2, 1},
                {52, 21, 1},
                {0, 0, 0},
                {0, 40, 40},
                {4, 8, 4}
        };
    }

    @Test(dataProvider = "findGcdData")
    public void testFindGcd(long a, long b, long answer) {
        Assert.assertEquals(gcdFinder.findGcd(a, b), answer);
    }
}