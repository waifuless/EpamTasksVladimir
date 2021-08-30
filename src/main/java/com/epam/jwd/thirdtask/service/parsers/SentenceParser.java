package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;
import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.model.Lexeme;
import com.epam.jwd.thirdtask.model.Sentence;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public class SentenceParser implements ComponentParser{

    @Override
    public TextComponent parse(String textToParse) {
        //todo: сделать, чтобы знаки приминания вносились и обрабатывались битовые выражения
        //todo: parse lexeme to parts?
        String[] arrOfLexemes = textToParse.split("\\s+");
        TextComponent sentence = new Sentence();
        for (String lexeme : arrOfLexemes) {
            sentence.addComponent(new Lexeme(lexeme));
        }
        return sentence;
    }

    @Override
    public TextComponent delegateParse(String textToParse) {
        throw new ActionNotSupportedException();
    }

    @Override
    public void execute(EnumSet<Commands> commands, TextComponent textComponent) {

    }
}
