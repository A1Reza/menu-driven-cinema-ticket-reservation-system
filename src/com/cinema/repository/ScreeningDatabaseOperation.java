package com.cinema.repository;

import com.cinema.model.Screening;

public class ScreeningDatabaseOperation implements DatabaseOperation {

    @Override
    public void save(Object object) {
        InMemoryDatabase.screenings.add(object);
    }

    @Override
    public void delete(long id) {
        for (int i = 0; i < InMemoryDatabase.screenings.size(); i++) {
            if (((Screening) InMemoryDatabase.screenings.get(i)).getId() == id) {
                InMemoryDatabase.screenings.remove(i);
                break;
            }
        }
    }

    @Override
    public void update(Object object) {
        Screening newObj = (Screening) object;
        for (int i = 0; i < InMemoryDatabase.screenings.size(); i++) {
            if (((Screening) InMemoryDatabase.screenings.get(i)).getId() == newObj.getId()) {
                InMemoryDatabase.screenings.set(i, newObj);
                break;
            }
        }
    }

    @Override
    public Object find(long id) {
        for (Object obj : InMemoryDatabase.screenings) {
            if (((Screening) obj).getId() == id) return obj;
        }
        return null;
    }
}