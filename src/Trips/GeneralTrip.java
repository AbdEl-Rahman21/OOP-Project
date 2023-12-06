package Trips;

import java.time.LocalDate;

public class GeneralTrip extends Trip {
    private final String[] mainFeatures = {"Cultural Experiences", "Adventure Activities", "Flexible Itineraries"};

    public GeneralTrip(String tripId, String mainTour, int seatPrice, int numberOfSeats, LocalDate endDate, LocalDate startDate) {
        super(tripId, mainTour, seatPrice, numberOfSeats, endDate, startDate);
    }

    @Override
    public  void displayTrip() {
        System.out.println("\nTrip Information");
        System.out.println("Trip ID: " + tripId);
        System.out.println("Trip Type: General");
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
