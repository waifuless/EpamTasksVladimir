package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.comparing;

import com.epam.jwd.secondtask.model.PlaneRegistrar;

import java.util.Comparator;

public class ComparatorByAngle<T extends PlaneRegistrar> implements Comparator<T> {

    private final Angle angle;

    ComparatorByAngle(Angle angle) {
        this.angle = angle;
    }

    public Angle getAngle() {
        return angle;
    }

    @Override
    public int compare(T elem1, T elem2) {
        switch (angle) {
            case OXY:
                return elem1.getAngleWithOxy().compareTo(elem2.getAngleWithOxy());
            case OXZ:
                return elem1.getAngleWithOxz().compareTo(elem2.getAngleWithOxz());
            case OYZ:
                return elem1.getAngleWithOyz().compareTo(elem2.getAngleWithOyz());
            default:
                throw new IllegalArgumentException();
        }
    }

    enum Angle {
        OXY, OXZ, OYZ
    }
}
