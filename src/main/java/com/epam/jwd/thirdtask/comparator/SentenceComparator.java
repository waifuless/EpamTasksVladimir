package com.epam.jwd.thirdtask.comparator;

import com.epam.jwd.thirdtask.model.TextComponent;

import java.util.Comparator;
import java.util.List;

public class SentenceComparator implements Comparator<TextComponent> {

    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        return Double.compare(findAverageWordLength(o1), findAverageWordLength(o2));
    }

    private double findAverageWordLength(TextComponent o) {
        List<TextComponent> lexemes = o.getComponents();
        double sumOfLexemeLength = 0.0;
        for (TextComponent lexeme : lexemes) {
            sumOfLexemeLength += lexeme.getText().length();
        }
        return sumOfLexemeLength / lexemes.size();
    }
}
