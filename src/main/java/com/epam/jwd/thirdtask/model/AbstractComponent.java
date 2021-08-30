package com.epam.jwd.thirdtask.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponent implements Component {

    private final List<Component> listOfComponents;

    public AbstractComponent() {
        listOfComponents = new ArrayList<>();
    }

    public AbstractComponent(List<Component> listOfComponents) {
        this.listOfComponents = listOfComponents;
    }

    @Override
    public void addComponent(Component component) {
        listOfComponents.add(component);
    }

    @Override
    public void removeComponent(Component component) {
        listOfComponents.remove(component);
    }

    @Override
    public List<Component> getComponents() {
        return listOfComponents;
    }
}
