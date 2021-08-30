package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.model.Component;
import com.epam.jwd.thirdtask.model.Paragraph;
import com.epam.jwd.thirdtask.model.Text;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public class ParagraphParser implements ComponentParser{

    private final SentenceParser lowerHandler = new SentenceParser();

    @Override
    public Component parse(String textToParse) {
        //todo: remake (\.|(...)|!|\?) - does not work
        String[] arrOfSentences = textToParse.trim().split("[.!?]");
        Component paragraph = new Paragraph();
        for (String sentence : arrOfSentences) {
            paragraph.addComponent(delegateParse(sentence));
        }
        return paragraph;
    }

    @Override
    public Component delegateParse(String textToParse) {
        return lowerHandler.parse(textToParse);
    }

    @Override
    public void execute(EnumSet<Commands> commands, Component component) {

    }
}
