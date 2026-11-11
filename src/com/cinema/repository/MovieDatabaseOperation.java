package com.cinema.repository;

import com.cinema.model.Movie;

public class MovieDatabaseOperation implements DatabaseOperation {

    @Override
    public void save(Object object) {
        InMemoryDatabase.movies.add(object);
    }

    @Override
    public void delete(long id) {
        for (int i = 0; i < InMemoryDatabase.movies.size(); i++) {
            if (((Movie) InMemoryDatabase.movies.get(i)).getId() == id) {
                InMemoryDatabase.movies.remove(i);
                break;
            }
        }
    }

    @Override
    public void update(Object object) {
        Movie newObj = (Movie) object;
        for (int i = 0; i < InMemoryDatabase.movies.size(); i++) {
            if (((Movie) InMemoryDatabase.movies.get(i)).getId() == newObj.getId()) {
                InMemoryDatabase.movies.set(i, newObj);
                break;
            }
        }
    }

    @Override
    public Object find(long id) {
        for (Object obj : InMemoryDatabase.movies) {
            if (((Movie) obj).getId() == id) return obj;
        }
        return null;
    }
}