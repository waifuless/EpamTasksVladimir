package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.comparing;

import com.epam.jwd.secondtask.model.PlaneRegistrar;

import java.util.Comparator;

public class ComparatorById<T extends PlaneRegistrar> implements Comparator<T> {

    ComparatorById() {
    }

    @Override
    public int compare(T elem1, T elem2) {
        return Long.compare(elem2.getId(), elem1.getId());
    }

}
