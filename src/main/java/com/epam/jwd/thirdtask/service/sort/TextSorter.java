package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.model.Text;
import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class TextSorter implements ComponentSorter{

    private final ParagraphSorter lowerSorter = ParagraphSorter.getInstance();

    private static TextSorter instance;

    private TextSorter(){}

    public static TextSorter getInstance() {
        if(instance==null){
            instance = new TextSorter();
        }
        return instance;
    }

    @Override
    public void sort(EnumSet<Commands> commands, TextComponent component) {
        List<TextComponent> listOfLowerComponents;
        if(commands.contains(Commands.SORT_PARAGRAPHS)){
            listOfLowerComponents = component.getComponents();
            //todo: bring to standalone comparator;
            listOfLowerComponents.sort(Comparator.comparingInt(x -> x.getComponents().size()));
            commands.remove(Commands.SORT_PARAGRAPHS);
        }
        if(!commands.isEmpty()){
            for (TextComponent lowerComponent : component.getComponents()) {
                delegateSort(commands.clone(), lowerComponent);
            }
        }
    }

    @Override
    public void delegateSort(EnumSet<Commands> commands, TextComponent component) {
        lowerSorter.sort(commands, component);
    }
}
