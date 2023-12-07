package Tickets;

import java.util.ArrayList;

public abstract class Ticket {
    final String tripId;
    final String ticketId;

    String bookedHotel;
    String bookedFlight;
    String bookedCarRental;

    int numberOfBookedSeats;

    ArrayList<String> activities = new ArrayList<>();

    public Ticket(String ticketId, String tripId, int numberOfBookedSeats) {
        this.ticketId = ticketId;
        this.tripId = tripId;
        this.numberOfBookedSeats = numberOfBookedSeats;
    }

    public String getTripId() {
        return tripId;
    }

    public String getTicketId() {
        return ticketId;
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
        this.activities = activities;
    }

    public void addActivity(String activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
        }

        System.out.println("Activity already booked!");
    }

    public void displayBookedActivities() {
        System.out.println("\nBooked Activities:-");

        for (int i = 1; i <= activities.size(); ++i) {
            System.out.println("[" + i + "] " + activities.get(i - 1) + ".");
        }
    }

    public abstract void displayTicket();
}

