package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.model.Paragraph;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public class ParagraphParser implements ComponentParser{

    private final SentenceParser lowerHandler = new SentenceParser();

    @Override
    public TextComponent parse(String textToParse) {
        //todo: remake (\.|(...)|!|\?) - does not work
        String[] arrOfSentences = textToParse.trim().split("[.!?]");
        TextComponent paragraph = new Paragraph();
        for (String sentence : arrOfSentences) {
            paragraph.addComponent(delegateParse(sentence));
        }
        return paragraph;
    }

    @Override
    public TextComponent delegateParse(String textToParse) {
        return lowerHandler.parse(textToParse);
    }

    @Override
    public void execute(EnumSet<Commands> commands, TextComponent textComponent) {

    }
}
