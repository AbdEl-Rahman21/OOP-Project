package Tickets;

public class SilverTicket extends Ticket{
    private final String bookedFeatures; // One booked feature

    public SilverTicket(String ticketId, String tripId, int numberOfBookedSeats, String bookedFeatures) {
        super(ticketId, tripId, numberOfBookedSeats);
        this.bookedFeatures = bookedFeatures;
    }

    @Override
    public void displayTicket() {
        System.out.println("\nTicket Information:-");
        System.out.println("Ticket Type: Silver");
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Trip ID: " + tripId);
        System.out.println("Number Of Booked Seats: " + numberOfBookedSeats);
        System.out.println("Booked Features: " + bookedFeatures);
        System.out.println("Booked Hotel: " + bookedHotel);
        System.out.println("Booked Car Rental: " + bookedCarRental);
        System.out.println("Booked Flights: " + bookedFlight);

        displayBookedActivities();
    }
}
