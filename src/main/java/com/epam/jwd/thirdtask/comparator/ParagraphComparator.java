package com.epam.jwd.thirdtask.comparator;

import com.epam.jwd.thirdtask.model.TextComponent;

import java.util.Comparator;

public class ParagraphComparator implements Comparator<TextComponent> {

    private static volatile ParagraphComparator instance;

    private ParagraphComparator() {
    }

    public static ParagraphComparator getInstance() {
        if (instance == null) {
            synchronized (ParagraphComparator.class) {
                if (instance == null) {
                    instance = new ParagraphComparator();
                }
            }
        }
        return instance;
    }

    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        return Integer.compare(o1.getComponents().size(), o2.getComponents().size());
    }
}
