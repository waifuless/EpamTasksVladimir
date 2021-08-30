package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;
import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

//todo: remove?
public class LexemeParser implements ComponentParser{

    @Override
    public TextComponent parse(String textToParse) {
        return null;
    }

    @Override
    public TextComponent delegateParse(String textToParse) {
        throw new ActionNotSupportedException();
    }

    @Override
    public void execute(EnumSet<Commands> commands, TextComponent textComponent) {

    }
}
