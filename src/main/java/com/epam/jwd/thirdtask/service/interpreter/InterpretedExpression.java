package com.epam.jwd.thirdtask.service.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InterpretedExpression {

    private final static Logger LOG = LogManager.getLogger(InterpretedExpression.class);
    private final static String RESULT_LOG_MCG = "Result of calculating: {}";
    private final static String INVALID_EXPRESSION_MCG = "Given expression is invalid";

    private final List<MinimalUnitInExpression> minimalUnitsList;

    public InterpretedExpression(List<MinimalUnitInExpression> minimalUnitsList) {
        this.minimalUnitsList = minimalUnitsList;
    }

    public InterpretedExpression() {
        minimalUnitsList = new ArrayList<>();
    }

    public List<MinimalUnitInExpression> getMinimalUnitsList() {
        return minimalUnitsList;
    }

    public void addMinimalUnitToExpression(MinimalUnitInExpression unit) {
        minimalUnitsList.add(unit);
    }

    public Integer calculate() {
        Stack<Integer> contextStack = new Stack<>();
        try {
            for (MinimalUnitInExpression minimalUnitInExpression : minimalUnitsList) {
                minimalUnitInExpression.interpret(contextStack);
            }
        } catch (ArithmeticException ex) {
            LOG.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            LOG.error(INVALID_EXPRESSION_MCG, ex);
            throw ex;
        }
        LOG.debug(RESULT_LOG_MCG, contextStack.peek());
        return contextStack.pop();
    }
}
