package com.epam.jwd.thirdtask.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTextComponent implements TextComponent {

    private final List<TextComponent> listOfTextComponents;

    public AbstractTextComponent() {
        listOfTextComponents = new ArrayList<>();
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
