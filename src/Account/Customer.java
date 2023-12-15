package Account;

import Trips.*;

import Tickets.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Customer {
    // Attributes
    private final int ID;

    private String name;
    private String email;
    private String phone;
    private String password;

    private int numberOfTrips = 0;

    final private ArrayList<String> preferences = new ArrayList<>();
    final private ArrayList<Ticket> bookedTickets = new ArrayList<>();

    public Customer(int id, String name, String email, String phone, String password) {
        this.ID = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Getters and Setters
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    public void setNumberOfTrips(int numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
    }

    public ArrayList<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(ArrayList<Ticket> bookedTickets) {
        this.bookedTickets.addAll(bookedTickets);
    }

    public void addTicket(Ticket ticket) {
        bookedTickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        bookedTickets.remove(ticket);
    }

    public ArrayList<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<String> preferences) {
        this.preferences.addAll(preferences);
    }

    // Methods
    public boolean verifyIdentity(int id, String password) {
        return ID == id && password.equals(this.password);
    }

    public void displayCustomer() {
        System.out.println("\nCustomer Information:-");
        System.out.println("Id: " + getID());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Password: " + getPassword());
        System.out.println("Number Of Trips: " + getNumberOfTrips());

        displayPreferences();
    }

    public void displayTravelHistory(ArrayList<Trip> trips) {
        System.out.println("\nTravel History:-");

        for (Ticket ticket : getBookedTickets()) {
            System.out.println("Ticket ID: " + ticket.getID());

            for (Trip trip : trips) {
                if (ticket.getTRIP_ID() == trip.getID()) {
                    System.out.println("Trip ID: " + trip.getID());
                    System.out.println("Main Tour: " + trip.getMainTour());
                    System.out.println("Start Date: " + trip.getStartDate());
                    System.out.println("End Date: " + trip.getEndDate());

                    break;
                }
            }

            System.out.println("Ticket Price: " + ticket.getPrice());
            System.out.println("Booked Seats: " + ticket.getNumberOfBookedSeats());
            System.out.println("Booked Hotel: " + ticket.getBookedHotel());
            System.out.println("Booked Flight: " + ticket.getBookedFlight());
            System.out.println("Booked Car Rental: " + ticket.getBookedCarRental());

            ticket.displayBookedActivities();
        }
    }

    public void addPreferences() {
        preferences.clear();

        String preference;

        Scanner INPUT = new Scanner(System.in);

        do {
            System.out.print("\nEnter preferences: ");
            preference = INPUT.nextLine();

            if (preferences.contains(preference)) {
                System.out.println("Preferences already exists!");
            } else {
                preferences.add(preference);
            }

            System.out.print("Press (Y) to add another preference or any other key to exit: ");
        } while (INPUT.nextLine().equalsIgnoreCase("y"));
    }

    public void displayPreferences() {
        System.out.println("\nPreferences:-");

        for (int i = 1; i <= preferences.size(); ++i) {
            System.out.println("[" + i + "] " + preferences.get(i - 1) + ".");
        }
    }
}
