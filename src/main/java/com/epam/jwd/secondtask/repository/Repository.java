package com.epam.jwd.secondtask.repository;

import java.util.Comparator;
import java.util.List;

/**
 * @param <T> - type of entity
 *            <p>
 *            Save can change entity, so return new entity(commonly it changes id)
 */
public interface Repository<T extends EntityWithId> {

    T save(T t);

    T findById(long id);

    long findId(T t);

    List<T> findAll();

    void update(T t, long id);

    void deleteById(long id);

    void deleteAll();

    long count();

    boolean isExistById(long id);

    boolean isExist(T t);

    List<T> findAllMatch(PredicateForRepository<T> predicate);

    void sort(Comparator<T> comparator);

    List<T> findAllMatchSorted(PredicateForRepository<T> predicate, Comparator<T> comparator);

}
