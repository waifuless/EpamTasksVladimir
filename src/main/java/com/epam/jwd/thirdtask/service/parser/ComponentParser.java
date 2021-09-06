package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.TextComponent;

public interface ComponentParser {

    /**
     * recursive method to composite
     */
    TextComponent parse(String textToParse);

    TextComponent delegateParse(String textToParse);
}
