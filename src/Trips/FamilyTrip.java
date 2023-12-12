package Trips;

import java.time.LocalDate;

public class FamilyTrip extends Trip {
    private final String[] mainFeatures = {"Kid-Friendly Activities", "Family-Friendly Accommodations", "Educational Tours"};

    public FamilyTrip(int id, String mainTour, int seatPrice, int numberOfSeats, LocalDate endDate, LocalDate startDate) {
        super(id, mainTour, seatPrice, numberOfSeats, endDate, startDate);
    }

    @Override
    public void displayTrip() {
        System.out.println("\nTrip Information:-");
        System.out.println("Trip ID: " + getID());
        System.out.println("Trip Type: Family");
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
