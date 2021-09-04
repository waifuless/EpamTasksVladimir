package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.Text;
import com.epam.jwd.thirdtask.model.TextComponent;

import java.util.regex.Pattern;

public class TextParser implements ComponentParser {

    private final static Pattern PARAGRAPH_DIVISOR_PATTERN = Pattern.compile("(?m)((?=^\\s{4})|(?=^\t))");

    private static volatile TextParser instance;

    private final ParagraphParser lowerHandler = ParagraphParser.getInstance();

    private TextParser() {
    }

    public static TextParser getInstance() {
        if (instance == null) {
            synchronized (TextParser.class) {
                if (instance == null) {
                    instance = new TextParser();
                }
            }
        }
        return instance;
    }

    @Override
    public TextComponent parse(String textToParse) {
        String[] arrOfParagraphs = PARAGRAPH_DIVISOR_PATTERN.split(textToParse.trim());
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
}
