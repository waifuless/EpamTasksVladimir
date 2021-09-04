package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.Paragraph;
import com.epam.jwd.thirdtask.model.TextComponent;

import java.text.BreakIterator;
import java.util.Locale;

public class ParagraphParser implements ComponentParser {

    private static volatile ParagraphParser instance;

    private final SentenceParser lowerHandler = SentenceParser.getInstance();

    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        if (instance == null) {
            synchronized (ParagraphParser.class) {
                if (instance == null) {
                    instance = new ParagraphParser();
                }
            }
        }
        return instance;
    }

    @Override
    public TextComponent parse(String textToParse) {
        TextComponent paragraph = new Paragraph();
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
        sentenceIterator.setText(textToParse);
        int start = sentenceIterator.first();
        for (int end = sentenceIterator.next(); end != BreakIterator.DONE; start = end, end = sentenceIterator.next()) {
            paragraph.addComponent(delegateParse(textToParse.substring(start, end)));
        }
        return paragraph;
    }

    @Override
    public TextComponent delegateParse(String textToParse) {
        return lowerHandler.parse(textToParse);
    }
}
