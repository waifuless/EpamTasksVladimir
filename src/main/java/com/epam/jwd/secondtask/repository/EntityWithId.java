package com.epam.jwd.secondtask.repository;

public interface EntityWithId {

    long getId();
    EntityWithId createWithId(long id);
}
