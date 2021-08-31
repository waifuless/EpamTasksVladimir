package com.epam.jwd.thirdtask.model;

import java.util.List;
import java.util.regex.Pattern;

public class Sentence extends AbstractTextComponent {

    @Override
    public String getText() {
        List<TextComponent> listOfComponents = super.getComponents();
        if (listOfComponents.isEmpty()) {
            return "";
        }
        Pattern marksWithoutPreviousSpace = Pattern.compile("[:,.!?)]");
        StringBuilder result = new StringBuilder();
        String textOfComponent;
        for (int i = 0, listOfComponentsSize = listOfComponents.size(); i < listOfComponentsSize - 1; i++) {
            result.append(listOfComponents.get(i).getText());
            if (!marksWithoutPreviousSpace.matcher(listOfComponents.get(i + 1).getText()).matches()) {
                result.append(" ");
            }
        }
        result.append(listOfComponents.get(listOfComponents.size() - 1).getText());
        return new String(result);
    }
}
