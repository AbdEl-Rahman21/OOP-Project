package Account;

import Tickets.*;
import Trips.*;
import java.util.ArrayList;

public class Customer {
    // Attributes
    private final int ID;

    private String name;
    private String email;
    private String phone;
    private String password;
    private String preferences;
    private int numberOfTrips = 0;

    private ArrayList<Ticket> bookedTickets = new ArrayList<>();

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
        this.bookedTickets = bookedTickets;
    }

    public void addTicket(Ticket ticket) {
        bookedTickets.add(ticket);

        // add voucher
    }
    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
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
        System.out.println("Preferences : "+ getPreferences());
    }
    public void tripsHistory(ArrayList<Trip> trips)
    {
        System.out.println("Trips History:-");
        for (Ticket tickets:bookedTickets)
        {
            System.out.println(tickets.getTRIP_ID());
            for (Trip trip:trips)
            {
                if (tickets.getTRIP_ID() == trip.getID())
                {
                    System.out.println(trip.getMainTour());
                    System.out.println(trip.getStartDate());
                    System.out.println(trip.getEndDate());
                }
            }
            System.out.println(tickets.getprice());
            System.out.println(tickets.getNumberOfBookedSeats());
            System.out.println(tickets.getBookedFlight());
            System.out.println(tickets.getBookedCarRental());
            for (String activities:tickets.getActivities())
                System.out.println(activities);

        }
    }
    public void addpreferences()
    {
        String preferences;

        do {
            System.out.print("\nEnter preferences: ");
            preferences = INPUT.nextLine();

            if (preferences.contains(preferences)) {
                System.out.println("preferences already exists!");
            } else {
                preferences.add(preferences);
            }

            System.out.print("Press (Y) to add another preferences or any other key to exit: ");
        } while (INPUT.nextLine().equalsIgnoreCase("y"));
    }
}
