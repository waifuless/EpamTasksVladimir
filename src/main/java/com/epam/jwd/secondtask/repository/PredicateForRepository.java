package com.epam.jwd.secondtask.repository;

public interface PredicateForRepository <T extends EntityWithId>{
    boolean test(T entity);
}
