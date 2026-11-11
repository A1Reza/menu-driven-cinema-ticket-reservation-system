package com.cinema.repository;

import com.cinema.model.Customer;

public class CustomerDatabaseOperation implements DatabaseOperation {

    @Override
    public void save(Object object) {
        InMemoryDatabase.customers.add(object);
    }

    @Override
    public void delete(long id) {
        for (int i = 0; i < InMemoryDatabase.customers.size(); i++) {
            if (((Customer) InMemoryDatabase.customers.get(i)).getId() == id) {
                InMemoryDatabase.customers.remove(i);
                break;
            }
        }
    }

    @Override
    public void update(Object object) {
        Customer newObj = (Customer) object;
        for (int i = 0; i < InMemoryDatabase.customers.size(); i++) {
            if (((Customer) InMemoryDatabase.customers.get(i)).getId() == newObj.getId()) {
                InMemoryDatabase.customers.set(i, newObj);
                break;
            }
        }
    }

    @Override
    public Object find(long id) {
        for (Object obj : InMemoryDatabase.customers) {
            if (((Customer) obj).getId() == id) return obj;
        }
        return null;
    }
}