package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.finding;

import com.epam.jwd.secondtask.model.PlaneRegistrar;
import com.epam.jwd.secondtask.repository.PredicateForRepository;

public class PredicateByIdRange<T extends PlaneRegistrar> implements PredicateForRepository<T> {

    private final long startOfRange;
    private final long endOfRange;

    public PredicateByIdRange(long startOfRange, long endOfRange) {
        this.startOfRange = startOfRange;
        this.endOfRange = endOfRange;
    }

    public long getStartOfRange() {
        return startOfRange;
    }

    public long getEndOfRange() {
        return endOfRange;
    }

    @Override
    public boolean test(T entity) {
        return entity.getId() >= startOfRange && entity.getId() <= endOfRange;
    }
}
