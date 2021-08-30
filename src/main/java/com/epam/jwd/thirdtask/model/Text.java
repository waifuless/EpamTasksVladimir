package com.epam.jwd.thirdtask.model;

import java.util.List;

public class Text extends AbstractComponent {

    @Override
    public String getText() {
        List<Component> listOfComponents = super.getComponents();
        StringBuilder result = new StringBuilder();
        for (Component component : listOfComponents) {
            result.append(component.getText()).append("\n");
        }
        return new String(result);
    }
}
