package com.epam.jwd.thirdtask.comparator;

import com.epam.jwd.thirdtask.model.TextComponent;

import java.util.Comparator;

public class MinimalUnitComparator implements Comparator<TextComponent> {

    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        //todo: change func
        return o1.getText().compareTo(o2.getText());
    }
}
