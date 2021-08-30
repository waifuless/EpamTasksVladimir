package com.epam.jwd.thirdtask.model;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;

import java.util.List;

public class Lexeme implements Component {

    protected String value;

    @Override
    public String getText() {
        return value;
    }

    @Override
    public void addComponent(Component component) {
        throw new ActionNotSupportedException();
    }

    @Override
    public void removeComponent(Component component) {
        throw new ActionNotSupportedException();
    }

    @Override
    public List<Component> getComponents() {
        throw new ActionNotSupportedException();
    }
}
