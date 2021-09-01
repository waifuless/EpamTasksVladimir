package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Command;

import java.util.Comparator;
import java.util.Map;

public interface TextComponentSorter {

    static TextComponentSorter getTextSorter() {
        return ParagraphSorterText.getInstance();
    }

    void sort(Map<Command, Comparator<TextComponent>> commands, TextComponent component);

    /**
     * If component can execute one of the command, it does. Commands go deeper recursively
     */
    void delegateSort(Map<Command, Comparator<TextComponent>> commands, TextComponent component);
}
