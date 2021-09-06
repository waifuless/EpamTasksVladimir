package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.Paragraph;
import com.epam.jwd.thirdtask.model.TextComponent;

import java.text.BreakIterator;
import java.util.Locale;

public class ParagraphParser extends AbstractNotFinalParser {

    private static volatile ParagraphParser instance;

    private ParagraphParser(ComponentParser lowerHandler) {
        super(lowerHandler);
    }

    public static ParagraphParser getInstance() {
        if (instance == null) {
            synchronized (ParagraphParser.class) {
                if (instance == null) {
                    instance = new ParagraphParser(SentenceParser.getInstance());
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
}
