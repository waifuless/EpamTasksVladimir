package com.epam.jwd.secondtask.repository;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.exception.repositoryexception.ExceptionInRepository;
import com.epam.jwd.secondtask.exception.repositoryexception.RepositoryExceptionsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryOnList<T extends EntityWithId> implements Repository<T> {

    private final static Logger LOG = LogManager.getLogger(RepositoryOnList.class);

    private final static long MINIMAL_ID_VALUE = 1;

    private final List<T> list;
    private long maxId;

    public RepositoryOnList() {
        this.maxId = 0;
        this.list = new ArrayList<>();
    }

    public RepositoryOnList(List<T> list) {
        if (list == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex);
            throw ex;
        }
        this.list = new ArrayList<>();
        for (T elem : list) {
            elem.createWithId(++maxId);
            this.list.add((T) elem.createWithId(++maxId));
        }
    }

    @Override
    public T save(T t) {
        if (t == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex);
            throw ex;
        }
        T newObject = (T) t.createWithId(++maxId);
        list.add(newObject);
        return newObject;
    }

    @Override
    public T findById(long id) {
        if (id < MINIMAL_ID_VALUE) {
            throw RepositoryExceptionsFactory.createAndLogException(ExceptionInRepository.INVALID_ID_EXCEPTION);
        }
        if (id > maxId) {
            throw RepositoryExceptionsFactory.createAndLogException(ExceptionInRepository.ENTITY_NOT_FOUND_EXCEPTION);
        }
        for (T t : list) {
            if (t.getId() == id) {
                return t;
            }
        }
        throw RepositoryExceptionsFactory.createAndLogException(ExceptionInRepository.ENTITY_NOT_FOUND_EXCEPTION);
    }

    @Override
    public long findId(T t) {
        if (t == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex);
            throw ex;
        }
        for (T elem : list) {
            if (elem.equals(t)) {
                return elem.getId();
            }
        }
        throw RepositoryExceptionsFactory.createAndLogException(ExceptionInRepository.ENTITY_NOT_FOUND_EXCEPTION);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void update(T newObject, long id) {
        if (id < MINIMAL_ID_VALUE || id > maxId) {
            throw RepositoryExceptionsFactory.createAndLogException(ExceptionInRepository.INVALID_ID_EXCEPTION);
        }
        if (newObject == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex);
            throw ex;
        }
        T oldObject = findById(id);
        list.set(list.indexOf(oldObject), (T) newObject.createWithId(id));
    }

    @Override
    public void deleteById(long id) {
        if (id < MINIMAL_ID_VALUE) {
            throw RepositoryExceptionsFactory.createAndLogException(ExceptionInRepository.INVALID_ID_EXCEPTION);
        }
        if (id > maxId) {
            throw RepositoryExceptionsFactory.createAndLogException(ExceptionInRepository.ENTITY_NOT_FOUND_EXCEPTION);
        }
        T oldObject = findById(id);
        list.remove(oldObject);
    }

    @Override
    public void deleteAll() {
        list.clear();
        maxId = 0;
    }

    @Override
    public long count() {
        return list.size();
    }

    @Override
    public boolean isExistById(long id) {
        if (id < MINIMAL_ID_VALUE) {
            throw RepositoryExceptionsFactory.createAndLogException(ExceptionInRepository.INVALID_ID_EXCEPTION);
        }
        if (id > maxId) {
            return false;
        }
        for (T t : list) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExist(T t) {
        if (t == null) {
            ArgumentNullException ex = new ArgumentNullException();
            LOG.error(ex);
            throw ex;
        }
        for (T elem : list) {
            if (elem.equals(t)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public List<T> findAllMatch(PredicateForRepository<T> predicate) {
        return list.stream().filter(predicate::test).collect(Collectors.toList());
    }


    @Override
    public void sort(Comparator<T> comparator) {
        list.sort(comparator);
    }


    @Override
    public List<T> findAllMatchSorted(PredicateForRepository<T> predicate, Comparator<T> comparator) {
        return list.stream().filter(predicate::test).sorted(comparator).collect(Collectors.toList());
    }
}
