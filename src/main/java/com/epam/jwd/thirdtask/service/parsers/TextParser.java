package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.model.Text;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public class TextParser implements ComponentParser{

    private final ParagraphParser lowerHandler = new ParagraphParser();

    @Override
    public TextComponent parse(String textToParse) {
        String[] arrOfParagraphs = textToParse.trim().split("(?m)((?=^\\s{4})|(?=^\t))");
        TextComponent text = new Text();
        for (String paragraph : arrOfParagraphs) {
            text.addComponent(delegateParse(paragraph));
        }
        return text;
    }

    @Override
    public TextComponent delegateParse(String textToParse) {
        return lowerHandler.parse(textToParse);
    }

    @Override
    public void execute(EnumSet<Commands> commands, TextComponent textComponent) {

    }
}
