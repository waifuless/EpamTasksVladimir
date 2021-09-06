package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Command;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParagraphSorterText extends AbstractNotFinalSorter {

    private static volatile ParagraphSorterText instance;

    private ParagraphSorterText(TextComponentSorter lowerSorter) {
        super(lowerSorter);
    }

    public static ParagraphSorterText getInstance() {
        if (instance == null) {
            synchronized (ParagraphSorterText.class) {
                if (instance == null) {
                    instance = new ParagraphSorterText(SentencesSorterText.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public void sort(Map<Command, Comparator<TextComponent>> commands, TextComponent component) {
        List<TextComponent> listOfLowerComponents;
        if (commands.containsKey(Command.SORT_PARAGRAPHS)) {
            listOfLowerComponents = component.getComponents();
            listOfLowerComponents.sort(commands.get(Command.SORT_PARAGRAPHS));
            commands.remove(Command.SORT_PARAGRAPHS);
        }
        if (!commands.isEmpty()) {
            for (TextComponent lowerComponent : component.getComponents()) {
                delegateSort(new HashMap<>(commands), lowerComponent);
            }
        }
    }
}
