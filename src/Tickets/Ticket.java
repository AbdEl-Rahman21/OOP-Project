package Tickets;

import java.util.ArrayList;

public abstract class Ticket {
    final int ID;
    final int TRIP_ID;

    float price = 0f;

    String bookedHotel = "";
    String bookedFlight = "";
    String bookedCarRental = "";

    int numberOfBookedSeats;

    final ArrayList<String> activities = new ArrayList<>();

    public Ticket(int id, int tripId, int numberOfBookedSeats) {
        this.ID = id;
        this.TRIP_ID = tripId;
        this.numberOfBookedSeats = numberOfBookedSeats;
    }

    public int getID() {
        return ID;
    }

    public int getTRIP_ID() {
        return TRIP_ID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBookedHotel() {
        return bookedHotel;
    }

    public void setBookedHotel(String bookedHotel) {
        this.bookedHotel = bookedHotel;
    }

    public String getBookedFlight() {
        return bookedFlight;
    }

    public void setBookedFlight(String bookedFlight) {
        this.bookedFlight = bookedFlight;
    }

    public String getBookedCarRental() {
        return bookedCarRental;
    }

    public void setBookedCarRental(String bookedCarRental) {
        this.bookedCarRental = bookedCarRental;
    }

    public int getNumberOfBookedSeats() {
        return numberOfBookedSeats;
    }

    public void setNumberOfBookedSeats(int numberOfBookedSeats) {
        this.numberOfBookedSeats = numberOfBookedSeats;
    }

    public ArrayList<String> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<String> activities) {
        this.activities.addAll(activities);
    }

    public void addActivity(String activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
        } else {
            System.out.println("Activity already booked!");
        }
    }

    public void displayBookedActivities() {
        System.out.println("\nBooked Activities:-");

        for (int i = 1; i <= activities.size(); ++i) {
            System.out.println("[" + i + "] " + activities.get(i - 1) + ".");
        }
    }

    public abstract void displayTicket();
}
