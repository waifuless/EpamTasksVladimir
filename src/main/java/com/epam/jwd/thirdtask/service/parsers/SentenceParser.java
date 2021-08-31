package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;
import com.epam.jwd.thirdtask.model.Expression;
import com.epam.jwd.thirdtask.model.MinimalUnit;
import com.epam.jwd.thirdtask.model.Sentence;
import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.Arrays;
import java.util.EnumSet;
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
        Pattern expression = Pattern.compile("\\d+\\p{Punct}+\\d+");
        TextComponent sentence = new Sentence();
        Matcher matcher;
        Consumer<String> addMinimalUnit = x -> sentence.addComponent(new MinimalUnit(x));
        for (String lexeme : arrOfLexemes) {
            matcher = expression.matcher(lexeme);
            if (matcher.matches()) {
                sentence.addComponent(new Expression(lexeme));
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

    @Override
    public void execute(EnumSet<Commands> commands, TextComponent textComponent) {

    }
}
