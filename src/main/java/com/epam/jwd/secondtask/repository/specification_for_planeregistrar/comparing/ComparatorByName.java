package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.comparing;

import com.epam.jwd.secondtask.model.PlaneRegistrar;

import java.util.Comparator;

public class ComparatorByName<T extends PlaneRegistrar> implements Comparator<T> {

    private final CompareMode mode;

    ComparatorByName(CompareMode mode) {
        this.mode = mode;
    }

    public CompareMode getMode() {
        return mode;
    }

    @Override
    public int compare(T elem1, T elem2) {
        switch (mode) {
            case COMMON:
                return elem1.getName().compareTo(elem2.getName());
            case IGNORE_CASE:
                return elem1.getName().compareToIgnoreCase(elem2.getName());
            default:
                throw new IllegalArgumentException();
        }
    }

    enum CompareMode {
        COMMON, IGNORE_CASE
    }

}
