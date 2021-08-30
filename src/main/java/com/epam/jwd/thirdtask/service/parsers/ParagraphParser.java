package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.model.Paragraph;
import com.epam.jwd.thirdtask.service.Commands;

import java.text.BreakIterator;
import java.util.EnumSet;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements ComponentParser{

    private final SentenceParser lowerHandler = new SentenceParser();

    @Override
    public TextComponent parse(String textToParse) {
        //todo: remake (\.|(...)|!|\?) - does not work
        /*
        String[] arrOfSentences = textToParse.trim().split("[.!?]");
        TextComponent paragraph = new Paragraph();
        for (String sentence : arrOfSentences) {
            paragraph.addComponent(delegateParse(sentence));
        }
        return paragraph;
         */
        //todo: replace that govno
        TextComponent paragraph = new Paragraph();
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
        sentenceIterator.setText(textToParse);
        int start = sentenceIterator.first();
        for (int end = sentenceIterator.next(); end != BreakIterator.DONE; start = end, end = sentenceIterator.next()) {
            paragraph.addComponent(delegateParse(textToParse.substring(start,end)));
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
