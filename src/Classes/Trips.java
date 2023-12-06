package Classes;

import java.util.ArrayList;

public abstract class Trips {
    String destination;
    int numberOfSeats;
    String startDate;
    String endDate;
    int pricePerSeat;
    String trip_ID;
    ArrayList<String> Activities;
    public abstract boolean checkValidity();
    public abstract void displayTrip();
}
