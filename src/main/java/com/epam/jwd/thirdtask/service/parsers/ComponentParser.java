package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.model.Component;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public interface ComponentParser {

    /**
     * recursive method to composite
     */
    Component parse(String textToParse);

    Component delegateParse(String textToParse);

    /**
     * If component can execute one of the command, it does. Commands go deeper recursively
     */
    void execute(EnumSet<Commands> commands, Component component);

}
