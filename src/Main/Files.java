package Main;
import java.time.LocalDate;
import Account.Customer;
import Trips.CoupleTrip;
import Trips.FamilyTrip;
import Trips.GeneralTrip;
import Trips.Trip;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Files {
    public File customersFile;
    public  File TripFile;

    public Files() {
        this.customersFile = new File("customer.txt");
        this.TripFile = new File("trip.txt");
    }

    public void writeTo(ArrayList<Customer> cust)
    {
        try {
            FileWriter myWriter = new FileWriter("customer.txt");
            for (int i = 0; i < cust.size() ; i++){  // number of customers needed
            myWriter.write(cust.get(i).getName() + "\n");
            myWriter.write( cust.get(i).getEmail()+ "\n");
            myWriter.write(cust.get(i).getPassword() + "\n");
            myWriter.write(cust.get(i).getPhone()+ "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFrom(ArrayList<Customer> cust) {
        try {
            File CustObj = new File("customer.txt");
            Scanner myReader = new Scanner(CustObj);
            while (myReader.hasNextLine()) {
                    cust.add(new Customer(myReader.nextLine(), myReader.nextLine(),myReader.nextLine(),myReader.nextLine(),myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToTrip(ArrayList<Trip> trips)
    {
        try {
            FileWriter myWriter = new FileWriter("trip.txt");
            for (int i = 0; i < trips.size() ; i++){  // number of customers needed
                if(trips.get(i)instanceof GeneralTrip)
                {
                    myWriter.write("g \n");
                }
                else if(trips.get(i) instanceof CoupleTrip)
                {
                    myWriter.write("c \n");
                } else  if(trips.get(i)instanceof FamilyTrip) {
                    myWriter.write("F \n");
                }
                myWriter.write(trips.get(i).getTripId()+ "\n");
                myWriter.write(trips.get(i).getMainTour() + "\n");
                myWriter.write(Integer.toString(trips.get(i).getSeatPrice()));
                myWriter.write("\n");
                myWriter.write(Integer.toString(trips.get(i).getNumberOfSeats()));
                myWriter.write("\n");
                myWriter.write(trips.get(i).getEndDate()+ "\n");
                myWriter.write(trips.get(i).getStartDate() + "\n");
                myWriter.write(Integer.toString(trips.get(i).getNumberOfBookedSeats()));
                myWriter.write("\n");
                myWriter.write(trips.get(i).getActivities().size() + "\n");
                for (int j = 0; j < trips.get(i).getActivities().size(); j++)
                {
                    myWriter.write(trips.get(i).getActivities().get(j) + "\n");
                }
                myWriter.write(trips.get(i).getCarRentals().size() + "\n");
                for (int j = 0; j < trips.get(i).getCarRentals().size(); j++)
                {
                    myWriter.write(trips.get(i).getCarRentals().get(j) + "\n");
                }
                myWriter.write(trips.get(i).getFlights().size() + "\n");
                for (int j = 0; j < trips.get(i).getFlights().size(); j++)
                {
                    myWriter.write(trips.get(i).getFlights().get(j) + "\n");
                }
                myWriter.write(trips.get(i).getHotels().size() + "\n");
                for (int j = 0; j < trips.get(i).getHotels().size(); j++)
                {
                    myWriter.write(trips.get(i).getHotels().get(j) + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFromTrip(ArrayList<Trip> trips) {
        int size;
        ArrayList <String> temp = new ArrayList<>();
        try {
            File CustObj = new File("trip.txt");
            Scanner myReader = new Scanner(CustObj);
            while (myReader.hasNextLine()) {
                if(myReader.nextLine().equals("g"))
                {
                    trips.add(new GeneralTrip(myReader.nextLine(),myReader.nextLine(),
                            Integer.valueOf(myReader.nextLine()),
                            Integer.valueOf(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine())));
                }
                else if(myReader.nextLine().equals("c"))
                {
                    trips.add(new CoupleTrip(myReader.nextLine(),myReader.nextLine(),
                            Integer.valueOf(myReader.nextLine()),
                            Integer.valueOf(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine())));
                } else  if(myReader.nextLine().equals("F")) {
                    trips.add(new FamilyTrip(myReader.nextLine(),myReader.nextLine(),
                            Integer.valueOf(myReader.nextLine()),
                            Integer.valueOf(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine())));
                }
                trips.get(trips.size()-1).setNumberOfBookedSeats(Integer.valueOf(myReader.nextLine()));
                size = Integer.valueOf(myReader.nextLine());
                for (int j = 0; j < size; j++)
                {
                    temp.add(myReader.nextLine());
                }
                trips.get(trips.size()-1).setActivities(temp);
                temp.clear();
                size = Integer.valueOf(myReader.nextLine());
                for (int j = 0; j < size; j++)
                {
                    temp.add(myReader.nextLine());
                }
                trips.get(trips.size()-1).setCarRentals(temp);
                temp.clear();
                size = Integer.valueOf(myReader.nextLine());
                for (int j = 0; j < size; j++)
                {
                    temp.add(myReader.nextLine());
                }
                trips.get(trips.size()-1).setFlights(temp);
                temp.clear();
                size = Integer.valueOf(myReader.nextLine());
                for (int j = 0; j < size; j++)
                {
                    temp.add(myReader.nextLine());
                }
                trips.get(trips.size()-1).setHotels(temp);
                temp.clear();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
