package com.epam.jwd.thirdtask.model;

import java.util.List;

public class Paragraph extends AbstractTextComponent {

    private final static String WHITE_SPACE = " ";

    @Override
    public String getText() {
        List<TextComponent> listOfComponents = super.getComponents();
        StringBuilder result = new StringBuilder();
        for (int i = 0, listOfComponentsSize = listOfComponents.size(); i < listOfComponentsSize - 1; i++) {
            result.append(listOfComponents.get(i).getText()).append(WHITE_SPACE);
        }
        if (!listOfComponents.isEmpty()) { //No white-space after last sentence
            result.append(listOfComponents.get(listOfComponents.size() - 1).getText());
        }
        return new String(result);
    }
}
