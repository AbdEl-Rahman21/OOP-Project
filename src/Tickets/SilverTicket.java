package Tickets;

public class SilverTicket extends Ticket {
    private String bookedFeatures; // One booked feature

    public SilverTicket(int id, int tripId, int numberOfBookedSeats, String bookedFeatures) {
        super(id, tripId, numberOfBookedSeats);
        this.bookedFeatures = bookedFeatures;
    }

    public String getBookedFeatures() {
        return bookedFeatures;
    }

    public void setBookedFeatures(String bookedFeatures) {
        this.bookedFeatures = bookedFeatures;
    }

    @Override
    public void displayTicket() {
        System.out.println("\nTicket Information:-");
        System.out.println("Ticket Type: Silver");
        System.out.println("Ticket ID: " + getID());
        System.out.println("Trip ID: " + getTRIP_ID());
        System.out.println("Ticket Price: " + getPrice());
        System.out.println("Number Of Booked Seats: " + getNumberOfBookedSeats());
        System.out.println("Booked Features: " + getBookedFeatures());
        System.out.println("Booked Hotel: " + getBookedHotel());
        System.out.println("Booked Car Rental: " + getBookedCarRental());
        System.out.println("Booked Flights: " + getBookedFlight());

        displayBookedActivities();
    }
}
