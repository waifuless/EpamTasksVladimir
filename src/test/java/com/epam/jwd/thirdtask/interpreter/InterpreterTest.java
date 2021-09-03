package com.epam.jwd.thirdtask.interpreter;

import com.epam.jwd.thirdtask.service.Interpreter;
import com.epam.jwd.thirdtask.service.PolishNoteCalculator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

class InterpreterTest {

    @Test
    void interpretToPolishNote() {
        String expression = "273+4|1+ (22+2)*21  + 2";
        final Pattern notWhiteSpacePattern = Pattern.compile("\\S+");
        String [] units = Arrays.stream(expression.split("(?<=\\D)|(?=\\D)"))
                .filter(s->notWhiteSpacePattern.matcher(s).matches())
                .toArray(String[]::new);
        for (String unit : units) {
            System.out.println(unit);
        }
    }

    @Test
    void testPol(){
        System.out.println(PolishNoteCalculator.getInstance().calculate(Interpreter.getInstance().interpretToPolishNote("((1+2)*3)/6/2*4+1")));
    }
}