package com.epam.jwd.secondtask.repository;

import com.epam.jwd.secondtask.exception.ArgumentNullException;
import com.epam.jwd.secondtask.exception.repositoryexception.EntityNotFoundException;
import com.epam.jwd.secondtask.exception.repositoryexception.InvalidIdException;

import java.util.ArrayList;
import java.util.List;

public class RepositoryOnList<T extends EntityWithId> implements Repository<T> {

    private final static long MINIMAL_ID_VALUE = 1;

    private final List<T> list;
    private long maxId;

    RepositoryOnList() {
        this.maxId = 0;
        this.list = new ArrayList<>();
    }

    RepositoryOnList(List<T> list) {
        if(list==null){
            throw new ArgumentNullException();
        }
        this.list = new ArrayList<>();
        for (T elem : list) {
            elem.createWithId(++maxId);
            this.list.add((T) elem.createWithId(++maxId));
        }
    }

    @Override
    public T save(T t) {
        if(t==null){
            throw new ArgumentNullException();
        }
        T newObject = (T) t.createWithId(++maxId);
        list.add(newObject);
        return newObject;
    }

    @Override
    public T findById(long id) {
        if(id<MINIMAL_ID_VALUE){
            throw new InvalidIdException();
        }
        if(id>maxId){
            throw new EntityNotFoundException();
        }
        for (T t : list) {
            if (t.getId() == id) {
                return t;
            }
        }
        //todo remake exceptions
        throw new EntityNotFoundException();
    }

    @Override
    public long findId(T t) {
        if(t==null){
            throw new ArgumentNullException();
        }
        for (T elem : list) {
            if (elem.equals(t)) {
                return elem.getId();
            }
        }
        //todo remake exceptions
        throw new EntityNotFoundException();
    }

    //todo: test that it`s deep clone
    @Override
    public List<T> findAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void update(T newObject, long id) {
        if(id<MINIMAL_ID_VALUE || id>maxId){
            throw new InvalidIdException();
        }
        if(newObject==null){
            throw new ArgumentNullException();
        }
        T oldObject = findById(id);
        list.set(list.indexOf(oldObject), (T) newObject.createWithId(id));
    }

    @Override
    public void deleteById(long id) {
        if(id<MINIMAL_ID_VALUE){
            throw new InvalidIdException();
        }
        if(id>maxId){
            throw new EntityNotFoundException();
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
        if(id<MINIMAL_ID_VALUE){
            throw new InvalidIdException();
        }
        if(id>maxId){
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
        if(t==null){
            throw new ArgumentNullException();
        }
        for (T elem : list) {
            if (elem.equals(t)) {
                return true;
            }
        }
        return false;
    }
}
