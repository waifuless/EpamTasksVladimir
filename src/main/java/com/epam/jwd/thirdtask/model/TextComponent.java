package com.epam.jwd.thirdtask.model;

import java.util.List;

public interface TextComponent {

    String getPreviousSymbols();

    String getAfterSymbols();

    String getText();

    void addComponent(TextComponent textComponent);

    void removeComponent(TextComponent textComponent);

    List<TextComponent> getComponents();
}
