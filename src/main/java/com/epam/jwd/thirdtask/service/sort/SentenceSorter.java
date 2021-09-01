package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;
import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class SentenceSorter implements ComponentSorter{

    private static SentenceSorter instance;

    private SentenceSorter(){}

    public static SentenceSorter getInstance() {
        if(instance == null){
            instance = new SentenceSorter();
        }
        return instance;
    }

    @Override
    public void sort(EnumSet<Commands> commands, TextComponent component) {
        List<TextComponent> listOfLowerComponents;
        if(commands.contains(Commands.SORT_LEXEMES)){
            listOfLowerComponents = component.getComponents();
            //todo: bring to standalone comparator;
            listOfLowerComponents.sort(Comparator.comparingInt(x -> x.getComponents().size()));
        }
    }

    @Override
    public void delegateSort(EnumSet<Commands> commands, TextComponent component) {
        throw new ActionNotSupportedException();
    }
}
