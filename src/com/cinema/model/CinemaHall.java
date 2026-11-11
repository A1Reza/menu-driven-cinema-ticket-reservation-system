package com.cinema.model;

public class CinemaHall extends BaseModel {
    private String hallName;
    private int totalSeats;

    public CinemaHall(long id, String hallName, int totalSeats) {
        super(id);
        this.hallName = hallName;
        this.totalSeats = totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    @Override
    public String toString() {
        return "Hall: \nName:" + hallName + ", Seats:" + totalSeats;
    }
}