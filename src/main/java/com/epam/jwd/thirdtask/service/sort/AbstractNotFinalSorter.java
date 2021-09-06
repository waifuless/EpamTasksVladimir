package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Command;

import java.util.Comparator;
import java.util.Map;

public abstract class AbstractNotFinalSorter implements TextComponentSorter {

    private final TextComponentSorter lowerSorter;

    protected AbstractNotFinalSorter(TextComponentSorter lowerSorter) {
        this.lowerSorter = lowerSorter;
    }

    @Override
    public void delegateSort(Map<Command, Comparator<TextComponent>> commands, TextComponent component) {
        lowerSorter.sort(commands, component);
    }
}
