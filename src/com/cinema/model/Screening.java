package com.cinema.model;
//(movie showings)
public class Screening extends BaseModel {
    private Movie movie;
    private CinemaHall hall;
    private WaitingQueue waitingQueue;
    private int reservedSeats;

    public Screening(long id, Movie movie, CinemaHall hall) {
        super(id);
        this.movie = movie;
        this.hall = hall;

        this.waitingQueue = new WaitingQueue();
        this.reservedSeats = 0;
    }

    public int getAvailableSeats() {
        return hall.getTotalSeats() - reservedSeats;
    }

    public void increaseReservedSeats() {
        reservedSeats++;
    }

    public void decreaseReservedSeats() {
        if (reservedSeats > 0) reservedSeats--;
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CinemaHall getHall() {
        return hall;
    }

    public void setHall(CinemaHall hall) {
        this.hall = hall;
    }

    public WaitingQueue getWaitingQueue() {
        return waitingQueue;
    }

    public void setWaitingQueue(WaitingQueue waitingQueue) {
        this.waitingQueue = waitingQueue;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    @Override
    public String toString() {
        return "Screening: \nMovie: " + movie.getTitle() + ", Hall: " + hall.getHallName() +
                ", AvailableSeats: " + getAvailableSeats();
    }
}
