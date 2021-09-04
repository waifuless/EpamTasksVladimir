package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Command;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentencesSorterText implements TextComponentSorter {

    private static volatile SentencesSorterText instance;

    private final MinimalUnitsSorterText lowerSorter = MinimalUnitsSorterText.getInstance();

    private SentencesSorterText() {
    }

    public static SentencesSorterText getInstance() {
        if (instance == null) {
            synchronized (SentencesSorterText.class) {
                if (instance == null) {
                    instance = new SentencesSorterText();
                }
            }
        }
        return instance;
    }

    @Override
    public void sort(Map<Command, Comparator<TextComponent>> commands, TextComponent component) {
        List<TextComponent> listOfLowerComponents;
        if (commands.containsKey(Command.SORT_SENTENCES)) {
            listOfLowerComponents = component.getComponents();
            listOfLowerComponents.sort(commands.get(Command.SORT_SENTENCES));
            commands.remove(Command.SORT_SENTENCES);
        }
        if (!commands.isEmpty()) {
            for (TextComponent lowerComponent : component.getComponents()) {
                delegateSort(new HashMap<>(commands), lowerComponent);
            }
        }
    }

    @Override
    public void delegateSort(Map<Command, Comparator<TextComponent>> commands, TextComponent component) {
        lowerSorter.sort(commands, component);
    }
}
