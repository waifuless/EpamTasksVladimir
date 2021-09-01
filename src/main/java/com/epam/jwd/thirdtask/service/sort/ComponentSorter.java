package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.EnumSet;

public interface ComponentSorter {

    void sort(EnumSet<Commands> commands, TextComponent component);

    /**
     * If component can execute one of the command, it does. Commands go deeper recursively
     */
    void delegateSort(EnumSet<Commands> commands, TextComponent component);
}
