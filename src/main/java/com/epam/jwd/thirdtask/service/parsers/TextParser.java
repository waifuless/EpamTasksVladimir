package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.model.Component;
import com.epam.jwd.thirdtask.model.Text;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public class TextParser implements ComponentParser{

    private final ParagraphParser lowerHandler = new ParagraphParser();

    @Override
    public Component parse(String textToParse) {
        //todo:remake to also use tabs. old = (?m)(?=^\s{4})
        String[] arrOfParagraphs = textToParse.trim().split("(?m)((?=^\\s{4})|(?=^\t))");
        Component text = new Text();
        for (String paragraph : arrOfParagraphs) {
            text.addComponent(delegateParse(paragraph));
        }
        return text;
    }

    @Override
    public Component delegateParse(String textToParse) {
        return lowerHandler.parse(textToParse);
    }

    @Override
    public void execute(EnumSet<Commands> commands, Component component) {

    }
}
