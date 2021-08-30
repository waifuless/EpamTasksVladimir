package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.model.Component;
import com.epam.jwd.thirdtask.model.Lexeme;
import com.epam.jwd.thirdtask.model.Sentence;
import com.epam.jwd.thirdtask.model.Text;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public class SentenceParser implements ComponentParser{

    private final LexemeParser lowerHandler = new LexemeParser();

    @Override
    public Component parse(String textToParse) {
        //todo: сделать, чтобы знаки приминания вносились и обрабатывались битовые выражения
        String[] arrOfLexemes = textToParse.trim().split("[^a-zA-Z]+");
        Component sentence = new Sentence();
        for (String lexeme : arrOfLexemes) {
            sentence.addComponent(new Lexeme(lexeme));
        }
        return sentence;
    }

    @Override
    public Component delegateParse(String textToParse) {
        return lowerHandler.parse(textToParse);
    }

    @Override
    public void execute(EnumSet<Commands> commands, Component component) {

    }
}
