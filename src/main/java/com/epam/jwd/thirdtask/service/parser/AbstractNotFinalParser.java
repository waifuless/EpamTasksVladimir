package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.TextComponent;

public abstract class AbstractNotFinalParser implements ComponentParser {

    private final ComponentParser lowerHandler;

    protected AbstractNotFinalParser(ComponentParser lowerHandler) {
        this.lowerHandler = lowerHandler;
    }

    @Override
    public TextComponent delegateParse(String textToParse) {
        return lowerHandler.parse(textToParse);
    }
}
