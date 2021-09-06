package com.epam.jwd.thirdtask.comparator;

import com.epam.jwd.thirdtask.model.MinimalUnit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimalUnitComparatorTest {

    public static Object[][] minimalUnitsCompareTestData() {
        return new Object[][]{
                {new MinimalUnit("abc"), new MinimalUnit("aabc"), 'a', -1},
                {new MinimalUnit("aAAabc"), new MinimalUnit("aaaabc"), 'a', 0},
                {new MinimalUnit("block"), new MinimalUnit("lock"), 'b', 1},
                {new MinimalUnit("block"), new MinimalUnit("lock"), 'B', 1}
        };
    }

    @ParameterizedTest
    @MethodSource("minimalUnitsCompareTestData")
    void compareTest(MinimalUnit unit1, MinimalUnit unit2, char compareChar, int result) {
        MinimalUnitComparator comparator = MinimalUnitComparator.by(compareChar);
        assertEquals(result, comparator.compare(unit1, unit2));
    }
}