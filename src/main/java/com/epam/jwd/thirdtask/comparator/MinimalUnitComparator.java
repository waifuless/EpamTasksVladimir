package com.epam.jwd.thirdtask.comparator;

import com.epam.jwd.thirdtask.model.TextComponent;

import java.util.Comparator;
import java.util.Locale;
import java.util.function.IntPredicate;

public class MinimalUnitComparator implements Comparator<TextComponent> {

    private final IntPredicate isCharSearchValuePredicate;

    private MinimalUnitComparator(char searchValue) {
        isCharSearchValuePredicate = c -> c == Character.toLowerCase(searchValue);
    }

    public static MinimalUnitComparator by(char searchValue) {
        return new MinimalUnitComparator(searchValue);
    }

    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        return Long.compare(findCountOfSearchValue(o1.getText()), findCountOfSearchValue(o2.getText()));
    }

    private long findCountOfSearchValue(String str) {
        return str.toLowerCase(Locale.ROOT).chars()
                .filter(isCharSearchValuePredicate)
                .count();
    }
}
