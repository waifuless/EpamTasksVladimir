package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;
import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Command;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MinimalUnitsSorterText implements TextComponentSorter {

    private static volatile MinimalUnitsSorterText instance;

    private MinimalUnitsSorterText() {
    }

    public static MinimalUnitsSorterText getInstance() {
        if (instance == null) {
            synchronized (MinimalUnitsSorterText.class) {
                if (instance == null) {
                    instance = new MinimalUnitsSorterText();
                }
            }
        }
        return instance;
    }

    @Override
    public void sort(Map<Command, Comparator<TextComponent>> commands, TextComponent component) {
        List<TextComponent> listOfLowerComponents;
        if (commands.containsKey(Command.SORT_MINIMAL_UNITS)) {
            listOfLowerComponents = component.getComponents();
            listOfLowerComponents.sort(commands.get(Command.SORT_MINIMAL_UNITS));
        }
    }

    @Override
    public void delegateSort(Map<Command, Comparator<TextComponent>> commands, TextComponent component) {
        throw new ActionNotSupportedException();
    }
}
