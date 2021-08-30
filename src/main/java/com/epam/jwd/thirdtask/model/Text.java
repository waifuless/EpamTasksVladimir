package com.epam.jwd.thirdtask.model;

import java.util.List;

public class Text extends AbstractTextComponent {

    @Override
    public String getText() {
        List<TextComponent> listOfComponents = super.getComponents();
        StringBuilder result = new StringBuilder();
        for (TextComponent component : listOfComponents) {
            result.append("\t").append(component.getText()).append("\n");
        }
        return new String(result);
    }
}
