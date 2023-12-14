package Tickets;

public class GoldTicket extends Ticket {

    private final float VOUCHER = 0.05f; // Voucher discount

    private String[] bookedFeatures; // Two booked features

    public GoldTicket(int id, int tripId, int numberOfBookedSeats, String[] bookedFeatures) {
        super(id, tripId, numberOfBookedSeats);

        this.bookedFeatures = bookedFeatures;
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
        System.out.println("Ticket Type: Gold");
        System.out.println("Ticket ID: " + getID());
        System.out.println("Trip ID: " + getTRIP_ID());
        System.out.println("Ticket Price: " + getPrice());
        System.out.println("Ticket Voucher (Next Trip): 5%");
        System.out.println("Number Of Booked Seats: " + getNumberOfBookedSeats());
        System.out.println("Booked Features: " + bookedFeatures[0] + " - " + bookedFeatures[1]);
        System.out.println("Booked Hotel: " + getBookedHotel());
        System.out.println("Booked Car Rental: " + getBookedCarRental());
        System.out.println("Booked Flights: " + getBookedFlight());

        displayBookedActivities();
    }
}
