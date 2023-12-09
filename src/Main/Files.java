package Main;

import Tickets.GoldTicket;
import Tickets.PlatinumTicket;
import Tickets.SilverTicket;
import Tickets.Ticket;
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
    public final static File tripFile = new File("trips.txt");
    public final static File ticketFile = new File("tickets.txt");

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

                myWriter.write(trip.getId() + "\n");
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

    public static void saveTickets(ArrayList<Ticket> tickets) {
        try {
            FileWriter myWriter = new FileWriter(customerFile);
            GoldTicket golden;
            SilverTicket silver;
            PlatinumTicket platinum;
            int bookedFeaturesSize = 0;
            for (Ticket ticket : tickets) {
                if (ticket instanceof SilverTicket)
                {
                    silver = (SilverTicket)ticket;
                    myWriter.write("s \n");
                    myWriter.write(silver.getId() + "\n");
                    myWriter.write(silver.getTripId() + "\n");
                    myWriter.write(Integer.toString(silver.getNumberOfBookedSeats()));
                    myWriter.write("\n");
                    myWriter.write(silver.getBookedFeatures());
                    myWriter.write(silver.getBookedHotel() + "\n");
                    myWriter.write(silver.getBookedFlight() + "\n");
                    myWriter.write(silver.getBookedCarRental() + "\n");
                    myWriter.write(Integer.toString(silver.getActivities().size()));
                    for (String activities : silver.getActivities())
                    {
                        myWriter.write(activities + "\n");
                    }
                } else if (ticket instanceof GoldTicket) {
                    golden = (GoldTicket)ticket;
                    myWriter.write("g \n");
                    myWriter.write(golden.getId() + "\n");
                    myWriter.write(golden.getTripId() + "\n");
                    myWriter.write(Integer.toString(golden.getNumberOfBookedSeats()));
                    myWriter.write("\n");
                    for (String bookedFeatures : golden.getBookedFeatures())
                    {
                        bookedFeaturesSize++;
                    }
                    myWriter.write(Integer.toString(bookedFeaturesSize));
                    for (String bookedFeatures : golden.getBookedFeatures())
                    {
                        myWriter.write(bookedFeatures);
                    }
                    myWriter.write(golden.getBookedHotel() + "\n");
                    myWriter.write(golden.getBookedFlight() + "\n");
                    myWriter.write(golden.getBookedCarRental() + "\n");
                    myWriter.write(Integer.toString(golden.getActivities().size()));
                    for (String activities : golden.getActivities())
                    {
                        myWriter.write(activities + "\n");
                    }
                } else if (ticket instanceof PlatinumTicket) {
                    platinum = (PlatinumTicket) ticket;
                    myWriter.write("p \n");
                    myWriter.write(platinum.getId() + "\n");
                    myWriter.write(platinum.getTripId() + "\n");
                    myWriter.write(Integer.toString(platinum.getNumberOfBookedSeats()));
                    myWriter.write("\n");
                    for (String bookedFeatures : platinum.getBookedFeatures())
                    {
                        bookedFeaturesSize++;
                    }
                    myWriter.write(Integer.toString(bookedFeaturesSize));
                    for (String bookedFeatures : platinum.getBookedFeatures())
                    {
                        myWriter.write(bookedFeatures);
                    }
                    myWriter.write(platinum.getBookedHotel() + "\n");
                    myWriter.write(platinum.getBookedFlight() + "\n");
                    myWriter.write(platinum.getBookedCarRental() + "\n");
                    myWriter.write(Integer.toString(platinum.getActivities().size()));
                    for (String activities : platinum.getActivities())
                    {
                        myWriter.write(activities + "\n");
                    }
                }

            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Can not save ticket data.");
            System.out.println(e.getMessage());
        }
    }

    public static void loadTickets(ArrayList<Ticket> tickets) {
        try {
            int size = 0;
            ArrayList<String> temp = new ArrayList<>();
            Scanner myReader = new Scanner(customerFile);
            while (myReader.hasNextLine()) {
                if (myReader.nextLine().equals("s")) {
                    tickets.add(new SilverTicket(myReader.nextLine(),
                            myReader.nextLine(),
                            Integer.valueOf(myReader.nextLine()),
                            myReader.nextLine()));
                    tickets.get(tickets.size()-1).setBookedHotel(myReader.nextLine());
                    tickets.get(tickets.size()-1).setBookedFlight(myReader.nextLine());
                    tickets.get(tickets.size()-1).setBookedCarRental(myReader.nextLine());
                    size = Integer.parseInt(myReader.nextLine());
                    for (int i = 0; i < size; i++)
                    {
                        temp.add(myReader.nextLine());
                    }
                    tickets.get(tickets.size()-1).setActivities(temp);
                }
                else if (myReader.nextLine().equals("g"))
                {

                    tickets.add(new PlatinumTicket(myReader.nextLine(),
                            myReader.nextLine(),
                            Integer.valueOf(myReader.nextLine()),
                            readArray(Integer.valueOf(myReader.nextLine()), myReader)));
                    tickets.get(tickets.size()-1).setBookedHotel(myReader.nextLine());
                    tickets.get(tickets.size()-1).setBookedFlight(myReader.nextLine());
                    tickets.get(tickets.size()-1).setBookedCarRental(myReader.nextLine());
                    size = Integer.parseInt(myReader.nextLine());
                    for (int i = 0; i < size; i++)
                    {
                        temp.add(myReader.nextLine());
                    }
                    tickets.get(tickets.size()-1).setActivities(temp);
                } else if (myReader.nextLine().equals("p"))
                {
                    tickets.add(new GoldTicket(myReader.nextLine(),
                            myReader.nextLine(),
                            Integer.valueOf(myReader.nextLine()),
                            readArray(Integer.valueOf(myReader.nextLine()), myReader)));
                    tickets.get(tickets.size()-1).setBookedHotel(myReader.nextLine());
                    tickets.get(tickets.size()-1).setBookedFlight(myReader.nextLine());
                    tickets.get(tickets.size()-1).setBookedCarRental(myReader.nextLine());
                    size = Integer.parseInt(myReader.nextLine());
                    for (int i = 0; i < size; i++)
                    {
                        temp.add(myReader.nextLine());
                    }
                    tickets.get(tickets.size()-1).setActivities(temp);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can not load customer data.");
            System.out.println(e.getMessage());
        }
    }

    public static String[] readArray(int size,Scanner myReader)
    {
        String arr[] = new String[size];
        for (int i = 0; i < size; i++)
        {
            arr[i] = myReader.nextLine();
        }
        return arr;
    }
}
