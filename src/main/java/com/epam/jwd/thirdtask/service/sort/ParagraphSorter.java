package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Commands;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class ParagraphSorter implements ComponentSorter{

    private final SentenceSorter lowerSorter = SentenceSorter.getInstance();

    private static ParagraphSorter instance;

    private ParagraphSorter(){}

    public static ParagraphSorter getInstance() {
        if(instance == null){
            instance = new ParagraphSorter();
        }
        return instance;
    }

    @Override
    public void sort(EnumSet<Commands> commands, TextComponent component) {
        List<TextComponent> listOfLowerComponents;
        if(commands.contains(Commands.SORT_SENTENCES)){
            listOfLowerComponents = component.getComponents();
            //todo: bring to standalone comparator;
            //Compare by average word length
            listOfLowerComponents.sort(Comparator.comparingDouble(x ->{
                List<TextComponent> lexemes = x.getComponents();
                double sumOfLexemeLength = 0.0;
                for (TextComponent lexeme : lexemes) {
                    sumOfLexemeLength += lexeme.getText().length();
                }
                return sumOfLexemeLength/lexemes.size();
            }));
            commands.remove(Commands.SORT_SENTENCES);
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
