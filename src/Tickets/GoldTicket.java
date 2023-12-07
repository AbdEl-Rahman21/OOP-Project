package Tickets;

public class GoldTicket extends Ticket{

    private final float VOUCHER = 0.05f; // Voucher discount
    
    private final String[] bookedFeatures; // Two booked features

    public GoldTicket(String ticketId, String tripId, int numberOfBookedSeats, String[] bookedFeatures) {
        super(ticketId, tripId, numberOfBookedSeats);
        
        this.bookedFeatures = bookedFeatures;
    }

    @Override
    public void displayTicket() {
        System.out.println("\nTicket Information:-");
        System.out.println("Ticket Type: Gold");
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Trip ID: " + tripId);
        System.out.println("Ticket Voucher (Next Trip): 5%");
        System.out.println("Number Of Booked Seats: " + numberOfBookedSeats);
        System.out.println("Booked Features: " + bookedFeatures[0] + " - " + bookedFeatures[1]);
        System.out.println("Booked Hotel: " + bookedHotel);
        System.out.println("Booked Car Rental: " + bookedCarRental);
        System.out.println("Booked Flights: " + bookedFlight);
        
        displayBookedActivities();
    }
}
