package com.cinema;

import com.cinema.model.*;
import com.cinema.service.CinemaOperationManagement;

import java.util.Scanner;

// REZA_ASADIPOUR_HW10_Maktab146
public class Main {
    public static void main(String[] args) {
        System.out.println("<<Cinema Ticket Reservation System>>\n");
        CinemaOperationManagement service = new CinemaOperationManagement();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n         ------------ Cinema Ticket Reservation System menu ------------");
            System.out.println("1. Add Customer | 2. Update Customer | 3. Delete Customer");
            System.out.println("4. Add Movie | 5. Update Movie | 6. Delete Movie");
            System.out.println("7. Add Cinema Hall | 8. Update Cinema Hall | 9. Delete Cinema Hall");
            System.out.println("10. Add Screening | 11. Update Screening | 12. Delete Screening");
            System.out.println("13. Reserve Ticket | 14. Cancel Reservation");
            System.out.println("15. Display Movies | 16. Display Screenings | 17. Display Waiting Queue by Screening ID");
            System.out.println("18. Exit");
            System.out.print("Please enter your choice number: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.println("(Add Customer)Please enter Customer's ID, firstName, lastName, phoneNumber, Username, Password: ");

                    System.out.print("ID: ");
                    long id = Long.parseLong(scanner.nextLine());

                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Username (must be at least 6 characters and unique): ");
                    String username = scanner.nextLine();

                    System.out.print("Password (must be at least 8 characters): ");
                    String password = scanner.nextLine();

                    Customer newAddCustomer = new Customer(id, firstName, lastName, phoneNumber, username, password);
                    service.addCustomer(newAddCustomer);

                }

                case 2 -> {
                    System.out.println("(Update Customer)Please enter Customer's ID, firstName, lastName, phoneNumber, Username, Password: ");

                    System.out.print("ID: ");
                    long id = Long.parseLong(scanner.nextLine());

                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Username (must be at least 6 characters and unique): ");
                    String username = scanner.nextLine();

                    System.out.print("Password (must be at least 8 characters): ");
                    String password = scanner.nextLine();

                    Customer newUpdateCustomer = new Customer(id, firstName, lastName, phoneNumber, username, password);
                    service.updateCustomer(newUpdateCustomer);
                }

                case 3 -> {
                    System.out.print("(Delete Customer)Please enter Customer's ID: ");
                    long id = Long.parseLong(scanner.nextLine());
                    service.deleteCustomer(id);
                }

                case 4 -> {
                    System.out.println("(Add Movie)Please enter Movie's ID, title, genre, durationInMinutes, imdbRate: ");

                    System.out.print("ID: ");
                    long id = Long.parseLong(scanner.nextLine());

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();

                    System.out.print("Duration (in minutes): ");
                    int durationInMinutes = Integer.parseInt(scanner.nextLine());

                    System.out.print("IMDB Rate (0.0 - 10.0): ");
                    double imdbRate = Double.parseDouble(scanner.nextLine());

                    Movie newAddMovie = new Movie(id, title, genre, durationInMinutes, imdbRate);
                    service.addMovie(newAddMovie);
                }

                case 5 -> {
                    System.out.println("(Update Movie)Please enter Movie's ID, title, genre, durationInMinutes, imdbRate: ");

                    System.out.print("ID: ");
                    long id = Long.parseLong(scanner.nextLine());

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();

                    System.out.print("Duration (in minutes): ");
                    int durationInMinutes = Integer.parseInt(scanner.nextLine());

                    System.out.print("IMDB Rate (0.0 - 10.0): ");
                    double imdbRate = Double.parseDouble(scanner.nextLine());

                    Movie newUpdateMovie = new Movie(id, title, genre, durationInMinutes, imdbRate);
                    service.updateMovie(newUpdateMovie);
                }

                case 6 -> {
                    System.out.print("(Delete Movie)Please enter Movie's ID: ");
                    long id = Long.parseLong(scanner.nextLine());
                    service.deleteMovie(id);
                }

                case 7 -> {
                    System.out.println("(Add CinemaHall)Please enter CinemaHall's ID, Name, totalSeats: ");

                    System.out.print("Hall ID: ");
                    long id = Long.parseLong(scanner.nextLine());

                    System.out.print("Hall Name (must be unique): ");
                    String hallName = scanner.nextLine();

                    System.out.print("Total Seats: ");
                    int totalSeats = Integer.parseInt(scanner.nextLine());

                    CinemaHall newAddHall = new CinemaHall(id, hallName, totalSeats);
                    service.addHall(newAddHall);
                }

                case 8 -> {
                    System.out.println("(Update CinemaHall)Please enter CinemaHall's ID, Name, totalSeats: ");

                    System.out.print("Hall ID: ");
                    long id = Long.parseLong(scanner.nextLine());

                    System.out.print("Hall Name (must be unique): ");
                    String hallName = scanner.nextLine();

                    System.out.print("Total Seats: ");
                    int totalSeats = Integer.parseInt(scanner.nextLine());

                    CinemaHall newUpdateHall = new CinemaHall(id, hallName, totalSeats);
                    service.updateHall(newUpdateHall);

                }

                case 9 -> {
                    System.out.print("(Delete Cinema Hall)Please enter CinemaHall's ID: ");
                    long id = Long.parseLong(scanner.nextLine());
                    service.deleteHall(id);
                }

                case 10 -> {
                    System.out.println("(Add Screening)Please enter Screening's ID, Its movie's title, Its CinemaHall's name: ");

                    System.out.print("Screening ID: ");
                    long screeningId = Long.parseLong(scanner.nextLine());

                    System.out.print("Movie Title: ");
                    String movieTitle = scanner.nextLine();

                    System.out.print("Cinema Hall Name: ");
                    String hallName = scanner.nextLine();


                    Movie movie = service.findMovieByTitle(movieTitle);
                    CinemaHall hall = service.findCinemaHallByName(hallName);

                    if (movie == null) {
                        System.out.println("Error: Movie '" + movieTitle + "' not found in DB.");
                    } else if (hall == null) {
                        System.out.println("Error: Hall '" + hallName + "' not found in DB.");
                    } else {
                        Screening newAddScreening = new Screening(screeningId, movie, hall);
                        service.addScreening(newAddScreening);
                    }
                }

                case 11 -> {
                    System.out.println("(Update Screening)Please enter Screening's ID, Its movie's title, Its CinemaHall's name: ");

                    System.out.print("Screening ID: ");
                    long screeningId = Long.parseLong(scanner.nextLine());

                    System.out.print("Movie Title: ");
                    String movieTitle = scanner.nextLine();

                    System.out.print("Cinema Hall Name: ");
                    String hallName = scanner.nextLine();


                    Movie movie = service.findMovieByTitle(movieTitle);
                    CinemaHall hall = service.findCinemaHallByName(hallName);

                    if (movie == null) {
                        System.out.println("Error: Movie '" + movieTitle + "' not found in DB.");
                    } else if (hall == null) {
                        System.out.println("Error: Hall '" + hallName + "' not found in DB.");
                    } else {
                        Screening newUpdateScreening = new Screening(screeningId, movie, hall);
                        service.updateScreening(newUpdateScreening);
                    }
                }

                case 12 -> {
                    System.out.print("(Delete Screening)Please enter Screening's ID: ");
                    long id = Long.parseLong(scanner.nextLine());
                    service.deleteScreening(id);
                }

                case 13 -> {
                    System.out.println("(Reserve Ticket) Please enter Customer ID and Screening ID: ");

                    System.out.print("Customer ID: ");
                    long customerId = scanner.nextLong();

                    System.out.print("Screening ID: ");
                    long screeningId = scanner.nextLong();
                    scanner.nextLine();

                    service.ticketReserve(customerId, screeningId);
                }

                case 14 -> {
                    System.out.print("(Cancel Ticket Reservation) Please enter the Ticket ID: ");
                    long ticketId = scanner.nextLong();
                    scanner.nextLine();

                    service.cancelTicketReservation(ticketId);

                }

                case 15 -> {
                    service.displayMovies();
                }

                case 16 -> {
                    service.displayScreenings();
                }

                case 17 -> {
                    System.out.print("Please enter Screening ID: ");
                    long screeningId = scanner.nextLong();
                    service.displayWaitingQueue(screeningId);
                }

                case 18 -> System.out.println("Exiting...");

                default -> System.out.println("Invalid Menu choice Number. Please try again. (from 1 to 18)");
            }
        } while (choice != 18);
        scanner.close();
    }
}
