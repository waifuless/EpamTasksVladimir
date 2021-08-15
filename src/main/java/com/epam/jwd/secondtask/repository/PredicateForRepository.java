package com.epam.jwd.secondtask.repository;

public interface PredicateForRepository <T>{
    boolean test(T entity);
}
