package com.cinema.model;

import java.time.LocalDateTime;

public class Ticket extends BaseModel {
    private Customer customer;
    private Screening screening;
    private LocalDateTime reservationTime;


    public Ticket(long id, Customer customer, Screening screening) {
        super(id);
        this.customer = customer;
        this.screening = screening;
        this.reservationTime = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Screening getScreening() {
        return screening;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    @Override
    public String toString() {
        return "Ticket: \nID: " + getId() +
                ", Customer: " + customer.getUsername() +
                ", Movie: " + screening.getMovie().getTitle() +
                ", Time: " + reservationTime.toString();
    }
}
