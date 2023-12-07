package Tickets;

public class PlatinumTicket extends Ticket {
    private final float VOUCHER = 0.1f; // Voucher discount

    private final String[] bookedFeatures; // Three booked features

    public PlatinumTicket(String id, String tripId, int numberOfBookedSeats, String[] bookedFeatures){
        super(id, tripId, numberOfBookedSeats);

        this.bookedFeatures = bookedFeatures;
        bookedCarRental = "VIP Car Rental"; // Free car rental
    }

    @Override
    public void displayTicket() {
        System.out.println("\nTicket Information:-");
        System.out.println("Ticket Type: Platinum");
        System.out.println("Ticket ID: " + id);
        System.out.println("Trip ID: " + tripId);
        System.out.println("Ticket Voucher (Next Trip): 10%");
        System.out.println("Number Of Booked Seats: " + numberOfBookedSeats);
        System.out.println("Booked Features: " + bookedFeatures[0] + " - " + bookedFeatures[1] + " - " +
                bookedFeatures[2]);
        System.out.println("Booked Hotel: " + bookedHotel);
        System.out.println("Booked Car Rental: " + bookedCarRental);
        System.out.println("Booked Flights: " + bookedFlight);

        displayBookedActivities();
    }

}
