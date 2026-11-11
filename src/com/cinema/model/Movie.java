package com.cinema.model;

public class Movie extends BaseModel {
    private String title;
    private String genre;
    private int durationInMinutes;
    private double imdbRate;

    public Movie(long id, String title, String genre, int durationInMinutes, double imdbRate) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
        this.imdbRate = imdbRate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public double getImdbRate() {
        return imdbRate;
    }

    public void setImdbRate(double imdbRate) {
        this.imdbRate = imdbRate;
    }

    @Override
    public String toString() {
        return "Movie: \nTitle:" + title + ", Rate:" + imdbRate;
    }
}