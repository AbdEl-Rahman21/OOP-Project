package Account;

import Tickets.*;

import java.util.ArrayList;

public class Customer {
    // Attributes
    private final int ID;

    private String name;
    private String email;
    private String phone;
    private String password;

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

    public void setBookedTickets(ArrayList<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public ArrayList<Ticket> getBookedTickets() {
        return bookedTickets;
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
    }
}
