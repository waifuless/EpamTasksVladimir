package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;
import com.epam.jwd.thirdtask.model.Component;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

//todo: remove?
public class LexemeParser implements ComponentParser{

    @Override
    public Component parse(String textToParse) {
        return null;
    }

    @Override
    public Component delegateParse(String textToParse) {
        throw new ActionNotSupportedException();
    }

    @Override
    public void execute(EnumSet<Commands> commands, Component component) {

    }
}
