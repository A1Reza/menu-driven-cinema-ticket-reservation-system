package com.cinema.repository;

import com.cinema.model.Ticket;

public class TicketDatabaseOperation implements DatabaseOperation {

    @Override
    public void save(Object object) {
        InMemoryDatabase.tickets.add(object);
    }

    @Override
    public void delete(long id) {
        for (int i = 0; i < InMemoryDatabase.tickets.size(); i++) {
            if (((Ticket) InMemoryDatabase.tickets.get(i)).getId() == id) {
                InMemoryDatabase.tickets.remove(i);
                break;
            }
        }
    }

    @Override
    public void update(Object object) {
        Ticket newObj = (Ticket) object;
        for (int i = 0; i < InMemoryDatabase.tickets.size(); i++) {
            if (((Ticket) InMemoryDatabase.tickets.get(i)).getId() == newObj.getId()) {
                InMemoryDatabase.tickets.set(i, newObj);
                break;
            }
        }
    }

    @Override
    public Object find(long id) {
        for (Object obj : InMemoryDatabase.tickets) {
            if (((Ticket) obj).getId() == id) return obj;
        }
        return null;
    }
}