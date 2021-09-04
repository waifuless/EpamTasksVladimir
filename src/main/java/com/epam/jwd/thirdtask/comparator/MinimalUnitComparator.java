package com.epam.jwd.thirdtask.comparator;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Interpreter;

import java.util.Comparator;

public class MinimalUnitComparator implements Comparator<TextComponent> {

    private static volatile MinimalUnitComparator instance;

    private MinimalUnitComparator() {
    }

    public static MinimalUnitComparator getInstance() {
        if (instance == null) {
            synchronized (MinimalUnitComparator.class) {
                if (instance == null) {
                    instance = new MinimalUnitComparator();
                }
            }
        }
        return instance;
    }

    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        //todo: change func
        return o1.getText().compareTo(o2.getText());
    }
}
