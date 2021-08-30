package com.epam.jwd.thirdtask.model;

import com.epam.jwd.thirdtask.exception.ActionNotSupportedException;

import java.util.List;

//todo: parse lexeme to parts?
public class Lexeme implements TextComponent {

    protected String value;

    public Lexeme(String value) {
        this.value = value;
    }

    @Override
    public String getText() {
        return value;
    }

    @Override
    public void addComponent(TextComponent component) {
        throw new ActionNotSupportedException();
    }

    @Override
    public void removeComponent(TextComponent component) {
        throw new ActionNotSupportedException();
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new ActionNotSupportedException();
    }
}
