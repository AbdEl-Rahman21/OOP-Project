package Trips;

import java.time.LocalDate;

public class FamilyTrip extends Trip {
    private final String[] mainFeatures = {"Kid-Friendly Activities", "Family-Friendly Accommodations", "Educational Tours"};

    public FamilyTrip(String id, String mainTour, int seatPrice, int numberOfSeats, LocalDate endDate, LocalDate startDate) {
        super(id, mainTour, seatPrice, numberOfSeats, endDate, startDate);
    }

    @Override
    public  void displayTrip() {
        System.out.println("\nTrip Information:-");
        System.out.println("Trip ID: " + id);
        System.out.println("Trip Type: Family");
        System.out.println("Main Tour: " + mainTour);
        System.out.println("Main Features: " + mainFeatures[0] + " - " + mainFeatures[1] + " - " + mainFeatures[2]);
        System.out.println("Seat Price: " + seatPrice);
        System.out.println("Number Of Seats: " + numberOfSeats);
        System.out.println("Number Of Booked Seats: " + numberOfBookedSeats);
        System.out.println("Number Of Available Seats: " + (numberOfSeats - numberOfBookedSeats));
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);

        displayTripDuration();
        displayHotels();
        displayFlights();
        displayCarRentals();
        displayActivities();
    }
}
