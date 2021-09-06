package com.epam.jwd.thirdtask.model;

import com.epam.jwd.thirdtask.service.PolishNoteParser;
import com.epam.jwd.thirdtask.service.interpreter.InterpretedExpression;
import com.epam.jwd.thirdtask.service.interpreter.PolishNoteInterpreter;

public class Expression extends MinimalUnit {

    private final static String INVALID_EXPRESSION_MCG = "<Expression is invalid: %s>";
    private final static PolishNoteParser POLISH_NOTE_PARSER = PolishNoteParser.getInstance();
    private final static PolishNoteInterpreter POLISH_NOTE_INTERPRETER = PolishNoteInterpreter.getInstance();

    private boolean expressionCalculated = false;
    private String calculatedAnswer;

    public Expression(String value) {
        super(value);
    }

    @Override
    public String getText() {
        if (!expressionCalculated) {
            try {
                InterpretedExpression expression = POLISH_NOTE_INTERPRETER.interpret(POLISH_NOTE_PARSER.parseToPolishNote(value));
                calculatedAnswer = String.valueOf(expression.calculate());
            } catch (Exception ex) {
                calculatedAnswer = String.format(INVALID_EXPRESSION_MCG, value);
            }
            expressionCalculated = true;
        }
        return calculatedAnswer;
    }
}
