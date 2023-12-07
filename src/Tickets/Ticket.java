package Tickets;
import java.util.ArrayList;
public abstract class Ticket {
    private final String ticketID;
    private final  String tripId;
    private int num_of_pocket_seats;
    private String Booked_Hotel;
    private String Booked_Flight;
    private String car_rental;
    private ArrayList<String> activities=new ArrayList<>();


    public Ticket(String ticketID,String tripID, int num_of_pocket_seats ){
        this.ticketID = ticketID;
        this.tripId =tripID;
        this.num_of_pocket_seats=num_of_pocket_seats;
    }

}

