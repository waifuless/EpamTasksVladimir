package com.epam.jwd.thirdtask.model;

import com.epam.jwd.thirdtask.service.Interpreter;
import com.epam.jwd.thirdtask.service.PolishNoteCalculator;

public class Expression extends MinimalUnit {

    private final static String INVALID_EXPRESSION_MCG = "<Expression is invalid: %s>";
    private final static Interpreter interpreter = Interpreter.getInstance();
    private final static PolishNoteCalculator polishCalculator = PolishNoteCalculator.getInstance();

    private boolean expressionCalculated = false;
    private String calculatedAnswer;

    public Expression(String value) {
        super(value);
    }

    @Override
    public String getText() {
        if (!expressionCalculated) {
            try {
                calculatedAnswer = String.valueOf(polishCalculator.calculate(interpreter.interpretToPolishNote(value)));
            } catch (Exception ex) {
                calculatedAnswer = String.format(INVALID_EXPRESSION_MCG, value);
            }
            expressionCalculated = true;
        }
        return calculatedAnswer;
    }
}
