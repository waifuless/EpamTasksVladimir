package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.finding;

import com.epam.jwd.secondtask.model.PlaneRegistrar;
import com.epam.jwd.secondtask.repository.PredicateForRepository;

import java.math.BigDecimal;

public class PredicateByPerpendicularAngle<T extends PlaneRegistrar> implements PredicateForRepository<T> {

    private final static BigDecimal PERPENDICULAR_ANGLE_VALUE = BigDecimal.valueOf(90);

    private final Angle angle;

    public PredicateByPerpendicularAngle(Angle angle) {
        this.angle = angle;
    }

    public Angle getAngle() {
        return angle;
    }

    @Override
    public boolean test(T entity) {
        switch (angle) {
            case OXY:
                return entity.getAngleWithOxy().compareTo(PERPENDICULAR_ANGLE_VALUE) == 0;
            case OXZ:
                return entity.getAngleWithOxz().compareTo(PERPENDICULAR_ANGLE_VALUE) == 0;
            case OYZ:
                return entity.getAngleWithOyz().compareTo(PERPENDICULAR_ANGLE_VALUE) == 0;
            default:
                throw new IllegalArgumentException();
        }
    }

    public enum Angle {
        OXY, OXZ, OYZ
    }
}
