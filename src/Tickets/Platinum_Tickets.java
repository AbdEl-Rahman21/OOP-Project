package Tickets;
import java.util.ArrayList;

public class Platinum_Tickets extends Ticket {
    private final float voucher = 0.1f;
    private final String []booked_features;

    public Platinum_Tickets(String ticketID,String tripID, int num_of_pocket_seats,String []booked_features){
        super(ticketID,tripID,num_of_pocket_seats);
        this.booked_features = booked_features;
        car_rental = "company car rental";
    }

    @Override
    public void display_ticket() {
        System.out.println("ticketID"+ticketID);
        System.out.println("tripID"+tripId);
        System.out.println("num_of_pocket_seats"+num_of_pocket_seats);
        System.out.println("activities");
        display_activity();
        for (String booked_features:booked_features) {
            System.out.println("booked features"+booked_features);
        }
        System.out.println("Hotel:"+Booked_Hotel);
        System.out.println("car rentals"+car_rental);
        System.out.println("Booked flights"+Booked_Flight);
    }

}
