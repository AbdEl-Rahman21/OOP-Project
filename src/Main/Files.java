package Main;

import Trips.*;

import Account.Customer;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Files {
    public final static File customerFile = new File("customers.txt");
    public final static File tripFile = new File("trips.txt");;

    public static void saveCustomers(ArrayList<Customer> customers) {
        try {
            FileWriter myWriter = new FileWriter(customerFile);

            for (Customer customer : customers) {
                myWriter.write(customer.getId() + "\n");
                myWriter.write(customer.getName() + "\n");
                myWriter.write(customer.getEmail() + "\n");
                myWriter.write(customer.getPhone() + "\n");
                myWriter.write(customer.getPassword() + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Can not save customer data.");
            System.out.println(e.getMessage());
        }
    }

    public static void loadCustomers(ArrayList<Customer> customers) {
        try {
            Scanner myReader = new Scanner(customerFile);

            while (myReader.hasNextLine()) {
                customers.add(new Customer(myReader.nextLine(), myReader.nextLine(), myReader.nextLine(),
                        myReader.nextLine(), myReader.nextLine()));
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can not load customer data.");
            System.out.println(e.getMessage());
        }
    }

    public static void saveTrips(ArrayList<Trip> trips) {
        try {
            FileWriter myWriter = new FileWriter(tripFile);

            for (Trip trip : trips) {  // number of customers needed
                if (trip instanceof GeneralTrip) {
                    myWriter.write("g\n");
                } else if (trip instanceof CoupleTrip) {
                    myWriter.write("c\n");
                } else if (trip instanceof FamilyTrip) {
                    myWriter.write("f\n");
                }

                myWriter.write(trip.getTripId() + "\n");
                myWriter.write(trip.getMainTour() + "\n");
                myWriter.write(trip.getSeatPrice() + "\n");
                myWriter.write(trip.getNumberOfSeats() + "\n");
                myWriter.write(trip.getEndDate() + "\n");
                myWriter.write(trip.getStartDate() + "\n");
                myWriter.write(trip.getNumberOfBookedSeats() + "\n");

                myWriter.write(trip.getHotels().size() + "\n");

                for (String hotel : trip.getHotels()) {
                    myWriter.write(hotel + "\n");
                }

                myWriter.write(trip.getFlights().size() + "\n");

                for (String flight : trip.getFlights()) {
                    myWriter.write(flight + "\n");
                }

                myWriter.write(trip.getCarRentals().size() + "\n");

                for (String carRental : trip.getCarRentals()) {
                    myWriter.write(carRental + "\n");
                }

                myWriter.write(trip.getActivities().size() + "\n");

                for (String activity : trip.getActivities()) {
                    myWriter.write(activity + "\n");
                }
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Can not save trip data.");
            System.out.println(e.getMessage());
        }
    }

    public static void loadTrips(ArrayList<Trip> trips) {
        int size = 0;

        ArrayList <String> temp = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(tripFile);

            while (myReader.hasNextLine()) {
                if (myReader.nextLine().equals("g")) {

                    trips.add(new GeneralTrip(myReader.nextLine(), myReader.nextLine(),
                            Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine()), LocalDate.parse(myReader.nextLine())));

                } else if (myReader.nextLine().equals("c")) {

                    trips.add(new CoupleTrip(myReader.nextLine(), myReader.nextLine(),
                            Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine()), LocalDate.parse(myReader.nextLine())));

                } else if (myReader.nextLine().equals("f")) {

                    trips.add(new FamilyTrip(myReader.nextLine(), myReader.nextLine(),
                            Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine()), LocalDate.parse(myReader.nextLine())));

                }

                trips.get(trips.size() - 1).setNumberOfBookedSeats(Integer.parseInt(myReader.nextLine()));

                size = Integer.parseInt(myReader.nextLine());

                for (int j = 0; j < size; ++j) {
                    temp.add(myReader.nextLine());
                }

                trips.get(trips.size() - 1).setHotels(temp);
                temp.clear();

                size = Integer.parseInt(myReader.nextLine());

                for (int j = 0; j < size; ++j) {
                    temp.add(myReader.nextLine());
                }

                trips.get(trips.size() - 1).setFlights(temp);
                temp.clear();

                size = Integer.parseInt(myReader.nextLine());

                for (int j = 0; j < size; ++j) {
                    temp.add(myReader.nextLine());
                }

                trips.get(trips.size() - 1).setCarRentals(temp);
                temp.clear();

                size = Integer.parseInt(myReader.nextLine());

                for (int j = 0; j < size; ++j) {
                    temp.add(myReader.nextLine());
                }

                trips.get(trips.size() - 1).setActivities(temp);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can not load trip data.");
            System.out.println(e.getMessage());
        }
    }
}
