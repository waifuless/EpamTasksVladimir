package com.epam.jwd.thirdtask.model;

import java.util.List;

public interface Component {

    String getText();

    void addComponent(Component component);

    void removeComponent(Component component);

    List<Component> getComponents();
}
