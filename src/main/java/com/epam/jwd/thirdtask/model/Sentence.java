package com.epam.jwd.thirdtask.model;

import java.util.List;
import java.util.regex.Pattern;

public class Sentence extends AbstractTextComponent {

    private final static Pattern MARKS_WITHOUT_PREVIOUS_SPACE = Pattern.compile("[:,.!?)]");
    private final static String WHITE_SPACE = " ";
    private final static String EMPTY_STRING = "";

    @Override
    public String getText() {
        List<TextComponent> listOfComponents = super.getComponents();
        if (listOfComponents.isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder result = new StringBuilder();
        String textOfComponent;
        for (int i = 0, listOfComponentsSize = listOfComponents.size(); i < listOfComponentsSize - 1; i++) {
            result.append(listOfComponents.get(i).getText());
            if (!MARKS_WITHOUT_PREVIOUS_SPACE.matcher(listOfComponents.get(i + 1).getText()).matches()) {
                result.append(WHITE_SPACE);
            }
        }
        result.append(listOfComponents.get(listOfComponents.size() - 1).getText());
        return new String(result);
    }
}
