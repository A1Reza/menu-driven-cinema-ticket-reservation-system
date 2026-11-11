package com.cinema.service;

import com.cinema.repository.*;
import com.cinema.model.*;


public class CinemaOperationManagement {

    private CustomerDatabaseOperation customerDBOp = new CustomerDatabaseOperation();
    private MovieDatabaseOperation movieDBOp = new MovieDatabaseOperation();
    private CinemaHallDatabaseOperation hallDBOp = new CinemaHallDatabaseOperation();
    private ScreeningDatabaseOperation screeningDBOp = new ScreeningDatabaseOperation();
    private TicketDatabaseOperation ticketDBOp = new TicketDatabaseOperation();


    public void addCustomer(Customer customer) {
        for (Object obj : InMemoryDatabase.customers) {
            if (((Customer) obj).getUsername().equals(customer.getUsername())) {
                System.out.println("Error: The username already exists in the database. It must be unique!");
                return;
            }
        }
        if (customer.getUsername().length() < 6 || customer.getPassword().length() < 8) {
            System.out.println("Error: Invalid credentials format! \nUsername must be at least 6 characters and " +
                    "Password must be at least 8 characters  ");
            return;
        }
        customerDBOp.save(customer);
        System.out.println("\nCustomer " + customer.getFirstName() + " " + customer.getLastName() + " added successfully in DB");
    }

    public void addMovie(Movie movie) {
        for (Object obj : InMemoryDatabase.movies) {
            if (((Movie) obj).getTitle().equalsIgnoreCase(movie.getTitle())) {
                System.out.println("Error: The Movie's title already exists in the database. It must be unique!");
                return;
            }
        }
        movieDBOp.save(movie);
        System.out.println("\nMovie " + movie.getTitle() + " added successfully in DB");
    }

    public void addHall(CinemaHall hall) {
        for (Object obj : InMemoryDatabase.halls) {
            if (((CinemaHall) obj).getHallName().equalsIgnoreCase(hall.getHallName())) {
                System.out.println("Error: The CinemaHall's name already exists in the database. It must be unique!");
                return;
            }
            if (hall.getTotalSeats() <= 0) {
                System.out.println("Error: total seats must be greater than 0.");
                return;
            }
        }
        hallDBOp.save(hall);
        System.out.println("\nCinemaHall " + hall.getHallName() + " added successfully in DB");
    }

    public void addScreening(Screening screening) {
        for (Object obj : InMemoryDatabase.screenings) {
            if (((Screening) obj).getId() == screening.getId()) {
                System.out.println("Error: The Screening's ID already exists in the database. It must be unique!");
                return;
            }
        }
        screeningDBOp.save(screening);
        System.out.println("\nScreening " + screening.getId() + " added successfully in DB");
    }


    public void updateCustomer(Customer customer) {
        if (customerDBOp.find(customer.getId()) != null) {
            for (Object obj : InMemoryDatabase.customers) {
                if (((Customer) obj).getUsername().equals(customer.getUsername())) {
                    System.out.println("Error: The username already exists in the database. It must be unique!");
                    return;
                }
            }
            if (customer.getUsername().length() < 6 || customer.getPassword().length() < 8) {
                System.out.println("Error: Invalid credentials format! \nUsername must be at least 6 characters and " +
                        "Password must be at least 8 characters  ");
                return;
            }
            customerDBOp.update(customer);
            System.out.println("Customer: " + customer.getFirstName() + " "
                    + customer.getLastName() + " was updated in DB"
            );
        } else {
            System.out.println("Customer was not found!");
        }
    }

    public void updateMovie(Movie movie) {
        if (movieDBOp.find(movie.getId()) != null) {
            for (Object obj : InMemoryDatabase.movies) {
                if (((Movie) obj).getTitle().equalsIgnoreCase(movie.getTitle())) {
                    System.out.println("Error: The Movie's title already exists in the database. It must be unique!");
                    return;
                }
            }
            movieDBOp.update(movie);
            System.out.println("Movie: " + movie.getTitle() + " with IMDB rate:"
                    + movie.getImdbRate() + " was updated in DB"
            );
        } else {
            System.out.println("Movie was not found!");
        }
    }

    public void updateHall(CinemaHall hall) {
        if (hallDBOp.find(hall.getId()) != null) {

            for (Object obj : InMemoryDatabase.halls) {
                if (((CinemaHall) obj).getHallName().equalsIgnoreCase(hall.getHallName())) {
                    System.out.println("Error: The CinemaHall's name already exists in the database. It must be unique!");
                    return;
                }
                if (hall.getTotalSeats() <= 0) {
                    System.out.println("Error: total seats must be greater than 0.");
                    return;
                }
            }

            hallDBOp.update(hall);
            System.out.println("CinemaHall: " + hall.getHallName() + " with number of total Seats:"
                    + hall.getTotalSeats() + " was updated in DB"
            );
        } else {
            System.out.println("CinemaHall was not found!");
        }
    }

    public void updateScreening(Screening screening) {
        if (screeningDBOp.find(screening.getId()) != null) {
            screeningDBOp.update(screening);
            System.out.println("Screening with ID: " + screening.getId() + " was updated in DB");
        } else {
            System.out.println("Screening was not found!");
        }
    }


    public void deleteCustomer(long id) {
        Customer customer = (Customer) customerDBOp.find(id);
        if (customer != null) {
            customerDBOp.delete(id);
            System.out.println("Customer: " + customer.getFirstName() + " "
                    + customer.getLastName() + " was deleted from DB"
            );
        } else {
            System.out.println("Customer was not found!");
        }
    }

    public void deleteMovie(long id) {
        Movie movie = (Movie) movieDBOp.find(id);
        if (movie != null) {
            movieDBOp.delete(id);
            System.out.println("Movie: " + movie.getTitle() + " with IMDB rate:"
                    + movie.getImdbRate() + " was deleted from DB"
            );
        } else {
            System.out.println("Movie was not found!");
        }
    }

    public void deleteHall(long id) {
        CinemaHall hall = (CinemaHall) hallDBOp.find(id);
        if (hall != null) {
            hallDBOp.delete(id);
            System.out.println("CinemaHall: " + hall.getHallName() + " with number of total Seats:"
                    + hall.getTotalSeats() + " was deleted from DB"
            );
        } else {
            System.out.println("CinemaHall was not found!");
        }
    }

    public void deleteScreening(long id) {
        Screening screening = (Screening) screeningDBOp.find(id);
        if (screening != null) {
            screeningDBOp.delete(id);
            System.out.println("Screening with ID: " + screening.getId() + " was deleted from DB");
        } else {
            System.out.println("Screening was not found!");
        }
    }


    public Customer findCustomerById(long id) {
        return (Customer) customerDBOp.find(id);
    }

    public Movie findMovieByTitle(String title) {
//        return (Movie) movieDBOp.find(id);
        for (Object obj : InMemoryDatabase.movies) {
            if (((Movie) obj).getTitle().equalsIgnoreCase(title)) {
                return (Movie) obj;
            }
        }
        return null;
    }

    public CinemaHall findCinemaHallByName(String hallName) {
//        return (CinemaHall) hallDBOp.find(id);
        for (Object obj : InMemoryDatabase.halls) {
            if (((CinemaHall) obj).getHallName().equalsIgnoreCase(hallName)) {
                return (CinemaHall) obj;
            }
        }
        return null;
    }

    public Screening findScreeningById(long id) {
        return (Screening) screeningDBOp.find(id);
    }

    public Ticket findTicketById(long id) {
        return (Ticket) ticketDBOp.find(id);
    }


    public void displayMovies() {
        if (InMemoryDatabase.movies.isEmpty()) {
            System.out.println("There is no movie in DB!");
        } else {
            System.out.println("List of movies in DB: ");
            for (Object obj : InMemoryDatabase.movies) {
                if (obj != null) {
                    Movie movie = (Movie) obj;
                    System.out.println(movie);
                    System.out.println();
                }
            }
        }
    }

    public void displayScreenings() {
        if (InMemoryDatabase.screenings.isEmpty()) {
            System.out.println("There is no screening in DB!");
        } else {
            System.out.println("List of screenings in DB: ");
            for (Object obj : InMemoryDatabase.screenings) {
                if (obj != null) {
                    Screening screening = (Screening) obj;
                    System.out.println(screening);
                    System.out.println();
                }
            }
        }
    }

    public void displayWaitingQueue(long screeningId) {
        Screening screening = findScreeningById(screeningId);

        if (screening == null) {
            System.out.println("Screening with id " + screeningId + " not found.");
            return;
        }


        if (screening.getWaitingQueue().isEmpty()) {
            System.out.println("Waiting queue is empty for screening id: " + screeningId);
            return;
        }

        System.out.println("Waiting queue for screening id: " + screeningId + ":");
        screening.getWaitingQueue().display();
    }


    public void ticketReserve(long customerId, long screeningId) {
        Customer customer = (Customer) customerDBOp.find(customerId);
        Screening screening = (Screening) screeningDBOp.find(screeningId);

        if (customer == null || screening == null) {
            System.out.println("Error: Customer or Screening(a movie showing) not found in DB!");
            return;
        }

        if (screening.getAvailableSeats() > 0) {
            Ticket newTicket = new Ticket(System.currentTimeMillis(), customer, screening);
            ticketDBOp.save(newTicket);
            screening.increaseReservedSeats();
            System.out.println("Reservation was successful with reservationTime: "
                    + newTicket.getReservationTime().toString() + " and Ticket ID: " + newTicket.getId()
            );
        } else {
            if (!screening.getWaitingQueue().add(customer)) {
                System.out.println("Error: There is no AvailableSeat and Waiting queue is also full!");
            } else {
                System.out.println("Screening full, " + customer.getFirstName() + " " + customer.getLastName()
                        + " added to waiting queue."
                );
            }
        }
    }

    public void cancelTicketReservation(long ticketId) {
        Ticket ticket = (Ticket) ticketDBOp.find(ticketId);
        if (ticket == null) {
            System.out.println("Error: ticket with ID: " + ticketId + " not found in DB!");
            return;
        }

        Screening screening = ticket.getScreening();
        ticketDBOp.delete(ticketId);
        screening.decreaseReservedSeats();
        System.out.println("Reservation " + ticketId + " canceled successfully.");

        if (!screening.getWaitingQueue().isEmpty()) {

            Customer nextCustomer = screening.getWaitingQueue().poll();
            System.out.println("Assigning the freed seat to: "
                    + nextCustomer.getFirstName() + " " + nextCustomer.getLastName()
            );
            ticketReserve(nextCustomer.getId(), screening.getId());
        }
    }

}