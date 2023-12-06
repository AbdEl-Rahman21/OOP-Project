package Classes;
import java.util.ArrayList;
import java.util.Scanner;

public class Family_Trip extends Trips{
    Scanner scanner = new Scanner(System.in);
    int choice;
    public Family_Trip(String destination , int numberOfSeats , String startDate , String endDate , int pricePerSeat , String trip_ID) {
        this.destination = destination;
        this.trip_ID = trip_ID;
        this.numberOfSeats = numberOfSeats;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pricePerSeat = pricePerSeat;
        Activities = new ArrayList<String>();
    }
    public void addActivites ()
    {
        do {
            Activities.add(scanner.nextLine());
            System.out.print("1 to add another activity\n 2 to cancel ");
            choice = scanner.nextInt();
        }while (choice==1);
    }

    public boolean checkValidity(int booked_Seats) {
        if (booked_Seats>=3&&booked_Seats<=numberOfSeats)
        {
            numberOfSeats =numberOfSeats-booked_Seats;
            return true;
        }
        else
        {
            return false;
        }
    }
    public  void displayTrip()
    {
System.out.print("number of avaliable seats"+numberOfSeats);
        System.out.print("Trip ID"+trip_ID);
        System.out.print("Trip destination"+destination);
        System.out.print("Seat Price "+pricePerSeat);
        System.out.print("Start Date"+startDate);
        System.out.print("End Date"+endDate);
        for (int i =0 ; i<Activities.size(); i++ )
        {
            System.out.print(Activities.get(i));
        }
    }
}
