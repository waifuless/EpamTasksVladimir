package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public interface ComponentParser {

    /**
     * recursive method to composite
     */
    TextComponent parse(String textToParse);

    TextComponent delegateParse(String textToParse);

    /**
     * If component can execute one of the command, it does. Commands go deeper recursively
     */
    void execute(EnumSet<Commands> commands, TextComponent textComponent);

}
