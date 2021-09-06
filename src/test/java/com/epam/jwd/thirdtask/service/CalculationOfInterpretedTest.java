package com.epam.jwd.thirdtask.service;

import com.epam.jwd.thirdtask.service.interpreter.PolishNoteInterpreter;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CalculationOfInterpretedTest {

    private final PolishNoteInterpreter polishNoteInterpreter = PolishNoteInterpreter.getInstance();

    public static Object[][] interpretTestData() {
        return new Object[][]{
                {19, Arrays.asList("2", "2", "+", "4", "3", "*", "+", "3", "+")},
                {300, Arrays.asList("2", "2", "12", "+", "21", "*", "+", "2", "2", "*", "+")},
                {82, Arrays.asList("5", "2", "2", "5", "2", "+", "*", "+", "*", "2", "|")}
        };
    }

    @ParameterizedTest
    @MethodSource("interpretTestData")
    void interpretAndCalculateToPolishNoteTest(int expected, List<String> actual) {
        assertEquals(expected, polishNoteInterpreter.interpret(actual).calculate());
    }

    @RepeatedTest(2)
    void testInterpreterIsSingleton() {
        assertSame(PolishNoteInterpreter.getInstance(), PolishNoteInterpreter.getInstance());
    }
}