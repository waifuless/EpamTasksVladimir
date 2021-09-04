package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;
import com.epam.jwd.thirdtask.model.Expression;
import com.epam.jwd.thirdtask.model.MinimalUnit;
import com.epam.jwd.thirdtask.model.Sentence;
import com.epam.jwd.thirdtask.model.TextComponent;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements ComponentParser {

    private static volatile SentenceParser instance;

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        if (instance == null) {
            synchronized (SentenceParser.class) {
                if (instance == null) {
                    instance = new SentenceParser();
                }
            }
        }
        return instance;
    }

    @Override
    public TextComponent parse(String textToParse) {
        String[] arrOfLexemes = textToParse.trim().split("\\s+");
        Pattern expression = Pattern.compile("\\(([\\d+\\-*/%|^&~()]|(<<)|(>>)|(>>>))+\\)");
        TextComponent sentence = new Sentence();
        Matcher matcher;
        for (String lexeme : arrOfLexemes) {
            matcher = expression.matcher(lexeme);
            if (matcher.find()) {
                splitAndAddMinimalUnitsToSentence(lexeme.substring(0, matcher.start()), sentence);
                sentence.addComponent(new Expression(matcher.group()));
                splitAndAddMinimalUnitsToSentence(lexeme.substring(matcher.end()), sentence);
                continue;
            }
            splitAndAddMinimalUnitsToSentence(lexeme, sentence);
        }
        return sentence;
    }

    @Override
    public TextComponent delegateParse(String textToParse) {
        throw new ActionNotSupportedException();
    }

    private void splitAndAddMinimalUnitsToSentence(String lexeme, TextComponent sentence) {
        if (lexeme.length() > 0) {
            Consumer<String> addMinimalUnit = x -> sentence.addComponent(new MinimalUnit(x));
            Arrays.stream(lexeme.split("((?<=\\p{Punct})|(?=\\p{Punct}))"))
                    .forEach(addMinimalUnit);
        }
    }
}
