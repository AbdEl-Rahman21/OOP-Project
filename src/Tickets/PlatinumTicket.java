package Tickets;

public class PlatinumTicket extends Ticket {
    private final float VOUCHER = 0.1f; // Voucher discount

    private String[] bookedFeatures; // Three booked features

    public PlatinumTicket(int id, int tripId, int numberOfBookedSeats, String[] bookedFeatures) {
        super(id, tripId, numberOfBookedSeats);

        this.bookedFeatures = bookedFeatures;
        this.bookedCarRental = "VIP Car Rental"; // Free car rental
    }

    public String[] getBookedFeatures() {
        return bookedFeatures;
    }

    public void setBookedFeatures(String[] bookedFeatures) {
        this.bookedFeatures = bookedFeatures;
    }

    @Override
    public void displayTicket() {
        System.out.println("\nTicket Information:-");
        System.out.println("Ticket Type: Platinum");
        System.out.println("Ticket ID: " + getID());
        System.out.println("Trip ID: " + getTRIP_ID());
        System.out.println("Ticket Price: " + getPrice());
        System.out.println("Ticket Voucher (Next Trip): 10%");
        System.out.println("Number Of Booked Seats: " + getNumberOfBookedSeats());
        System.out.println("Booked Features: " + bookedFeatures[0] + " - " + bookedFeatures[1] + " - " +
                bookedFeatures[2]);
        System.out.println("Booked Hotel: " + getBookedHotel());
        System.out.println("Booked Car Rental: " + getBookedCarRental());
        System.out.println("Booked Flights: " + getBookedFlight());

        displayBookedActivities();
    }
}
