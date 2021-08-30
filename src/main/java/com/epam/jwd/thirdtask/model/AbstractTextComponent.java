package com.epam.jwd.thirdtask.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTextComponent implements TextComponent {

    private final List<TextComponent> listOfTextComponents;

    public AbstractTextComponent() {
        listOfTextComponents = new ArrayList<>();
    }

    public AbstractTextComponent(List<TextComponent> listOfTextComponents) {
        this.listOfTextComponents = listOfTextComponents;
    }

    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component : listOfTextComponents) {
            result.append(component.getPreviousSymbols())
                    .append(component.getText())
                    .append(component.getAfterSymbols());
        }
        return new String(result);
    }

    @Override
    public void addComponent(TextComponent textComponent) {
        listOfTextComponents.add(textComponent);
    }

    @Override
    public void removeComponent(TextComponent textComponent) {
        listOfTextComponents.remove(textComponent);
    }

    @Override
    public List<TextComponent> getComponents() {
        return listOfTextComponents;
    }
}
