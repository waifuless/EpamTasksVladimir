package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;
import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Command;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MinimalUnitsSorter implements TextComponentSorter {

    private static volatile MinimalUnitsSorter instance;

    private MinimalUnitsSorter() {
    }

    public static MinimalUnitsSorter getInstance() {
        if (instance == null) {
            synchronized (MinimalUnitsSorter.class) {
                if (instance == null) {
                    instance = new MinimalUnitsSorter();
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
