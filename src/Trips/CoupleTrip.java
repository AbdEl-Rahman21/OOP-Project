package Trips;

import java.time.LocalDate;

public class CoupleTrip extends Trip {
    private static final String[] mainFeatures = {"Romantic Dinners", "Couples' Spa Day", "Private Excursions"};

    public CoupleTrip(int id, String mainTour, int seatPrice, int numberOfSeats, LocalDate endDate, LocalDate startDate) {
        super(id, mainTour, seatPrice, numberOfSeats, endDate, startDate);
    }

    public static String[] getMainFeatures() {
        return mainFeatures;
    }

    @Override
    public void displayTrip() {
        System.out.println("\nTrip Information:-");
        System.out.println("Trip ID: " + getID());
        System.out.println("Trip Type: Couple");
        System.out.println("Main Tour: " + getMainTour());
        System.out.println("Main Features: " + mainFeatures[0] + " - " + mainFeatures[1] + " - " + mainFeatures[2]);
        System.out.println("Seat Price: " + getSeatPrice());
        System.out.println("Number Of Seats: " + getNumberOfSeats());
        System.out.println("Number Of Booked Seats: " + getNumberOfBookedSeats());
        System.out.println("Number Of Available Seats: " + (getNumberOfSeats() - getNumberOfBookedSeats()));
        System.out.println("Start Date: " + getStartDate());
        System.out.println("End Date: " + getEndDate());

        displayTripDuration();
        displayHotels();
        displayFlights();
        displayCarRentals();
        displayActivities();
    }
}
