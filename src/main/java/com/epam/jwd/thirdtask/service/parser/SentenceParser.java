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

    private static SentenceParser instance;

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        if (instance == null) {
            instance = new SentenceParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String textToParse) {
        String[] arrOfLexemes = textToParse.trim().split("\\s+");
        Pattern expression = Pattern.compile("\\(([\\d+\\-*/%|^&~()]|(<<)|(>>)|(>>>))+\\)");
        TextComponent sentence = new Sentence();
        Matcher matcher;
        Consumer<String> addMinimalUnit = x -> sentence.addComponent(new MinimalUnit(x));
        for (String lexeme : arrOfLexemes) {
            matcher = expression.matcher(lexeme);
            if (matcher.find()) {
                //todo: bring adding to private method and remove double space
                Arrays.stream(lexeme.substring(0, matcher.start()).split("((?<=\\p{Punct})|(?=\\p{Punct}))"))
                        .forEach(addMinimalUnit);
                sentence.addComponent(new Expression(matcher.group()));
                Arrays.stream(lexeme.substring(matcher.end()).split("((?<=\\p{Punct})|(?=\\p{Punct}))"))
                        .forEach(addMinimalUnit);
                continue;
            }
            Arrays.stream(lexeme.split("((?<=\\p{Punct})|(?=\\p{Punct}))"))
                    .forEach(addMinimalUnit);
        }
        return sentence;
    }

    @Override
    public TextComponent delegateParse(String textToParse) {
        throw new ActionNotSupportedException();
    }
}
