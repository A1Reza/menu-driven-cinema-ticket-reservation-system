package com.cinema.repository;

import com.cinema.model.CinemaHall;

public class CinemaHallDatabaseOperation implements DatabaseOperation {

    @Override
    public void save(Object object) {
        InMemoryDatabase.halls.add(object);
    }

    @Override
    public void delete(long id) {
        for (int i = 0; i < InMemoryDatabase.halls.size(); i++) {
            if (((CinemaHall) InMemoryDatabase.halls.get(i)).getId() == id) {
                InMemoryDatabase.halls.remove(i);
                break;
            }
        }
    }

    @Override
    public void update(Object object) {
        CinemaHall newObj = (CinemaHall) object;
        for (int i = 0; i < InMemoryDatabase.halls.size(); i++) {
            if (((CinemaHall) InMemoryDatabase.halls.get(i)).getId() == newObj.getId()) {
                InMemoryDatabase.halls.set(i, newObj);
                break;
            }
        }
    }

    @Override
    public Object find(long id) {
        for (Object obj : InMemoryDatabase.halls) {
            if (((CinemaHall) obj).getId() == id) return obj;
        }
        return null;
    }
}