package com.epam.jwd.thirdtask.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolishNoteCalculatorTest {

    private final PolishNoteCalculator calculator = PolishNoteCalculator.getInstance();

    public static Object[][] interpretTestData() {
        return new Object[][]{
                {19, Arrays.asList("2", "2", "+", "4", "3", "*", "+", "3", "+")},
                {300, Arrays.asList("2", "2", "12", "+", "21", "*", "+", "2", "2", "*", "+")},
                {82, Arrays.asList("5", "2", "2", "5", "2", "+", "*", "+", "*", "2", "|")}
        };
    }

    @ParameterizedTest
    @MethodSource("interpretTestData")
    void interpretToPolishNoteTest(int expected, List<String> actual) {
        assertEquals(expected, calculator.calculate(actual));
    }
}