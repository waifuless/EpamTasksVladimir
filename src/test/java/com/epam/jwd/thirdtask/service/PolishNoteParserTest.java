package com.epam.jwd.thirdtask.service;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class PolishNoteParserTest {

    private final PolishNoteParser polishNoteParser = PolishNoteParser.getInstance();

    public static Object[][] interpretTestData() {
        return new Object[][]{
                {Arrays.asList("2", "2", "+", "4", "3", "*", "+", "3", "+"), "2+2+4*3+3"},
                {Arrays.asList("2", "2", "12", "+", "21", "*", "+", "2", "2", "*", "+"), "2+(2+12)*21+2*2"},
                {Arrays.asList("5", "2", "2", "5", "2", "+", "*", "+", "*", "2", "|"), "5*(2+2*(5+2))|2"}
        };
    }

    @ParameterizedTest
    @MethodSource("interpretTestData")
    void interpretToPolishNoteTest(List<String> expected, String actual) {
        assertEquals(expected, polishNoteParser.parseToPolishNote(actual));
    }

    @RepeatedTest(2)
    void testInterpreterIsSingleton() {
        assertSame(PolishNoteParser.getInstance(), PolishNoteParser.getInstance());
    }
}