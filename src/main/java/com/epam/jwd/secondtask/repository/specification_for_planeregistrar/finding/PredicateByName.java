package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.finding;

import com.epam.jwd.secondtask.model.PlaneRegistrar;
import com.epam.jwd.secondtask.repository.PredicateForRepository;

public class PredicateByName<T extends PlaneRegistrar> implements PredicateForRepository<T> {

    private final String name;
    private final CompareMode mode;

    public PredicateByName(String name) {
        this.name = name;
        mode = CompareMode.COMMON;
    }

    public PredicateByName(String name, CompareMode mode) {
        this.name = name;
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public CompareMode getMode() {
        return mode;
    }

    @Override
    public boolean test(T entity) {
        switch (mode) {
            case COMMON:
                return entity.getName().equals(name);
            case IGNORE_CASE:
                return entity.getName().equalsIgnoreCase(name);
            default:
                throw new IllegalArgumentException();
        }
    }

    public enum CompareMode {
        COMMON, IGNORE_CASE
    }
}
