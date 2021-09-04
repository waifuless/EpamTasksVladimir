package com.epam.jwd.thirdtask.model;

import java.util.List;

public class Text extends AbstractTextComponent {

    private final static String ELEM_BEFORE_PARAGRAPH = "\t";
    private final static String ELEM_AFTER_PARAGRAPH = "\n";

    @Override
    public String getText() {
        List<TextComponent> listOfComponents = super.getComponents();
        StringBuilder result = new StringBuilder();
        for (TextComponent component : listOfComponents) {
            result.append(ELEM_BEFORE_PARAGRAPH).append(component.getText()).append(ELEM_AFTER_PARAGRAPH);
        }
        return new String(result);
    }
}
