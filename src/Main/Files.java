package Main;

import Trips.*;

import Tickets.*;

import Account.*;

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

    public static void saveCustomers(int lastTicketId, ArrayList<Customer> customers) {
        try {
            FileWriter myWriter = new FileWriter(customerFile);

            myWriter.write(lastTicketId + "\n");

            for (Customer customer : customers) {
                myWriter.write(customer.getID() + "\n");
                myWriter.write(customer.getName() + "\n");
                myWriter.write(customer.getEmail() + "\n");
                myWriter.write(customer.getPhone() + "\n");
                myWriter.write(customer.getPassword() + "\n");
                myWriter.write(customer.getNumberOfTrips() + "\n");

                myWriter.write(customer.getPreferences().size() + "\n");

                for (String preferences : customer.getPreferences()) {
                    myWriter.write(preferences + "\n");
                }

                saveTickets(customer.getBookedTickets());
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Can not save customer data.");
            System.out.println(e.getMessage());
        }
    }

    public static int loadCustomers(ArrayList<Customer> customers) {
        int size;

        int lastTicketId = 0;

        ArrayList<String> temp = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(customerFile);

            if (myReader.hasNextLine()) {
                lastTicketId = Integer.parseInt(myReader.nextLine());
            }

            while (myReader.hasNextLine()) {
                customers.add(new Customer(Integer.parseInt(myReader.nextLine()), myReader.nextLine(),
                        myReader.nextLine(), myReader.nextLine(), myReader.nextLine()));

                customers.get(customers.size() - 1).setNumberOfTrips(Integer.parseInt(myReader.nextLine()));

                size = Integer.parseInt(myReader.nextLine());

                for (int j = 0; j < size; ++j) {
                    temp.add(myReader.nextLine());
                }

                customers.get(customers.size() - 1).setPreferences(temp);
                temp.clear();

                loadTickets(customers.get(customers.size() - 1).getBookedTickets());
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can not load customer data.");
            System.out.println(e.getMessage());
        }

        return lastTicketId;
    }

    public static void saveTrips(ArrayList<Trip> trips) {
        try {
            FileWriter myWriter = new FileWriter(tripFile);

            for (Trip trip : trips) {
                if (trip instanceof GeneralTrip) {
                    myWriter.write("g\n");
                } else if (trip instanceof CoupleTrip) {
                    myWriter.write("c\n");
                } else if (trip instanceof FamilyTrip) {
                    myWriter.write("f\n");
                }

                myWriter.write(trip.getID() + "\n");
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
        int size;

        ArrayList<String> temp = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(tripFile);

            while (myReader.hasNextLine()) {
                if (myReader.nextLine().equals("g")) {

                    trips.add(new GeneralTrip(Integer.parseInt(myReader.nextLine()), myReader.nextLine(),
                            Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine()), LocalDate.parse(myReader.nextLine())));

                } else if (myReader.nextLine().equals("c")) {

                    trips.add(new CoupleTrip(Integer.parseInt(myReader.nextLine()), myReader.nextLine(),
                            Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()),
                            LocalDate.parse(myReader.nextLine()), LocalDate.parse(myReader.nextLine())));

                } else if (myReader.nextLine().equals("f")) {

                    trips.add(new FamilyTrip(Integer.parseInt(myReader.nextLine()), myReader.nextLine(),
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
        int bookedFeaturesSize = 0;

        try {
            FileWriter myWriter = new FileWriter(ticketFile);

            myWriter.write(tickets.size() + "\n");

            GoldTicket gold;
            SilverTicket silver;
            PlatinumTicket platinum;

            for (Ticket ticket : tickets) {
                if (ticket instanceof SilverTicket) {
                    silver = (SilverTicket) ticket;

                    myWriter.write("s\n");

                    myWriter.write(silver.getID() + "\n");
                    myWriter.write(silver.getTRIP_ID() + "\n");
                    myWriter.write(silver.getNumberOfBookedSeats() + "\n");
                    myWriter.write(silver.getBookedFeatures() + "\n");

                    myWriter.write(silver.getPrice() + "\n");

                    myWriter.write(silver.getBookedHotel() + "\n");
                    myWriter.write(silver.getBookedFlight() + "\n");
                    myWriter.write(silver.getBookedCarRental() + "\n");

                    myWriter.write(silver.getActivities().size() + "\n");

                    for (String activity : silver.getActivities()) {
                        myWriter.write(activity + "\n");
                    }
                } else if (ticket instanceof GoldTicket) {
                    gold = (GoldTicket) ticket;

                    myWriter.write("g\n");

                    myWriter.write(gold.getID() + "\n");
                    myWriter.write(gold.getTRIP_ID() + "\n");
                    myWriter.write(gold.getNumberOfBookedSeats() + "\n");

                    for (String bookedFeature : gold.getBookedFeatures()) {
                        myWriter.write(bookedFeature + "\n");
                    }

                    myWriter.write(gold.getPrice() + "\n");

                    myWriter.write(gold.getBookedHotel() + "\n");
                    myWriter.write(gold.getBookedFlight() + "\n");
                    myWriter.write(gold.getBookedCarRental() + "\n");

                    myWriter.write(gold.getActivities().size() + "\n");

                    for (String activity : gold.getActivities()) {
                        myWriter.write(activity + "\n");
                    }
                } else if (ticket instanceof PlatinumTicket) {
                    platinum = (PlatinumTicket) ticket;

                    myWriter.write("p \n");

                    myWriter.write(platinum.getID() + "\n");
                    myWriter.write(platinum.getTRIP_ID() + "\n");
                    myWriter.write(platinum.getNumberOfBookedSeats() + "\n");

                    for (String bookedFeature : platinum.getBookedFeatures()) {
                        myWriter.write(bookedFeature + "\n");
                    }

                    myWriter.write(platinum.getPrice() + "\n");

                    myWriter.write(platinum.getBookedHotel() + "\n");
                    myWriter.write(platinum.getBookedFlight() + "\n");
                    myWriter.write(platinum.getBookedCarRental() + "\n");

                    myWriter.write(platinum.getActivities().size() + "\n");

                    for (String activity : platinum.getActivities()) {
                        myWriter.write(activity + "\n");
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
        int size;

        int numberOfBookedTickets = 0;

        ArrayList<String> temp = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(ticketFile);

            if (myReader.hasNextLine()) {
                numberOfBookedTickets = Integer.parseInt(myReader.nextLine());
            }

            for (int i = 0; i < numberOfBookedTickets; ++i) {
                if (myReader.nextLine().equals("s")) {

                    tickets.add(new SilverTicket(Integer.parseInt(myReader.nextLine()),
                            Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()),
                            myReader.nextLine()));

                } else if (myReader.nextLine().equals("g")) {

                    tickets.add(new PlatinumTicket(Integer.parseInt(myReader.nextLine()),
                            Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()),
                            readArray(2, myReader)));

                } else if (myReader.nextLine().equals("p")) {

                    tickets.add(new GoldTicket(Integer.parseInt(myReader.nextLine()),
                            Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()),
                            readArray(3, myReader)));

                }

                tickets.get(tickets.size() - 1).setPrice(Float.parseFloat(myReader.nextLine()));

                tickets.get(tickets.size() - 1).setBookedHotel(myReader.nextLine());
                tickets.get(tickets.size() - 1).setBookedFlight(myReader.nextLine());
                tickets.get(tickets.size() - 1).setBookedCarRental(myReader.nextLine());

                size = Integer.parseInt(myReader.nextLine());

                for (int j = 0; j < size; ++j) {
                    temp.add(myReader.nextLine());
                }

                tickets.get(tickets.size() - 1).setActivities(temp);
                temp.clear();
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can not load ticket data.");
            System.out.println(e.getMessage());
        }
    }

    public static String[] readArray(int size, Scanner myReader) {
        String[] array = new String[size];

        for (int i = 0; i < size; ++i) {
            array[i] = myReader.nextLine();
        }

        return array;
    }
}
