package com.epam.jwd.thirdtask.model;

import java.util.List;

public class Sentence extends AbstractComponent {

    @Override
    public String getText() {
        List<Component> listOfComponents = super.getComponents();
        StringBuilder result = new StringBuilder();
        for (Component component : listOfComponents) {
            result.append(" ").append(component.getText()).append(" ");
        }
        return new String(result);
    }
}
