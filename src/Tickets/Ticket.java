package Tickets;
import java.util.ArrayList;
public abstract class Ticket {
    final String ticketID;
    final  String tripId;
    int num_of_pocket_seats;
    String Booked_Hotel;
    String Booked_Flight;
    String car_rental;
    ArrayList<String> activities=new ArrayList<>();


    public Ticket(String ticketID,String tripID, int num_of_pocket_seats ){
        this.ticketID = ticketID;
        this.tripId =tripID;
        this.num_of_pocket_seats=num_of_pocket_seats;
    }

    public String getTripId() {
        return tripId;

    }

    public String getTicketID() {
        return ticketID;
    }

    public String getBooked_Flight() {
        return Booked_Flight;
    }

    public String getBooked_Hotel() {
        return Booked_Hotel;
    }

    public String getCar_rental() {
        return car_rental;
    }

    public void setActivities(ArrayList<String> activities) {
        this.activities = activities;
    }

    public void setBooked_Flight(String booked_Flight) {
        Booked_Flight = booked_Flight;
    }

    public int getNum_of_pocket_seats() {
        return num_of_pocket_seats;
    }

    public void setBooked_Hotel(String booked_Hotel) {
        Booked_Hotel = booked_Hotel;
    }

    public void setCar_rental(String car_rental) {
        this.car_rental = car_rental;
    }

    public void setNum_of_pocket_seats(int num_of_pocket_seats) {
        this.num_of_pocket_seats = num_of_pocket_seats;
    }

    public ArrayList<String> getActivities() {
        return activities;
    }
    public boolean check_activity(String activity){
        if(activities.contains(activity)){
            return true;
        }
        else {
            return false;
        }
    }
    public void add_activity(String activity){
        activities.add(activity);
    }
    public abstract void display_ticket();

    public void display_activity(){
        for (String activity:activities) {
            System.out.println(activity);
        }
    }
}

