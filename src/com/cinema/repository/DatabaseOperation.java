package com.cinema.repository;

public interface DatabaseOperation {

    void save(Object object);

    void delete(long id);

    void update(Object object);

    Object find(long id);
}