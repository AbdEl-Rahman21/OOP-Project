package Tickets;

public class silver extends Ticket{

    private final String booked_features;

    public silver (String ticketID, String tripID, int num_of_pocket_seats, String booked_features) {
        super(ticketID, tripID, num_of_pocket_seats);
        this.booked_features = booked_features;

    }

    @Override
    public void display_ticket() {
        System.out.println("ticketID" + getTicketID());
        System.out.println("tripID" + getTripId());
        System.out.println("num_of_pocket_seats"+num_of_pocket_seats);
        System.out.println("activities");
        display_activity();

            System.out.println("booked features" + booked_features);

        System.out.println("Hotel:" + getBooked_Hotel());
        System.out.println("car rentals" + getCar_rental());
        System.out.println("Booked flights" + getBooked_Flight());
    }
}
