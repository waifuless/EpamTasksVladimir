package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.model.Lexeme;
import com.epam.jwd.thirdtask.model.Sentence;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public class SentenceParser implements ComponentParser{

    private final LexemeParser lowerHandler = new LexemeParser();

    @Override
    public TextComponent parse(String textToParse) {
        //todo: сделать, чтобы знаки приминания вносились и обрабатывались битовые выражения
        String[] arrOfLexemes = textToParse.trim().split("[^a-zA-Z]+");
        TextComponent sentence = new Sentence();
        for (String lexeme : arrOfLexemes) {
            sentence.addComponent(new Lexeme(lexeme));
        }
        return sentence;
    }

    @Override
    public TextComponent delegateParse(String textToParse) {
        return lowerHandler.parse(textToParse);
    }

    @Override
    public void execute(EnumSet<Commands> commands, TextComponent textComponent) {

    }
}
