package com.epam.jwd.thirdtask.model;

import java.util.List;

public class Paragraph extends AbstractComponent {

    @Override
    public String getText() {
        List<Component> listOfComponents = super.getComponents();
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (Component component : listOfComponents) {
            result.append(component.getText());
        }
        return new String(result);
    }
}
