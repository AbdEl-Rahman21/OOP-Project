package Main;

import Trips.*;

import Tickets.*;

import Account.*;

import java.time.LocalDate;
import java.time.DateTimeException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    private final static ArrayList<TourGuide> tourGuides = new ArrayList<>();
    private static String lastTicketId; // Stored to generate new IDs
    private final static ArrayList<Trip> trips = new ArrayList<>();
    private final static ArrayList<Customer> customers = new ArrayList<>();
    private static int user = -2;  // -1 for admin or customer ID
    private final static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void run() {
        do {
            if (welcomeUser() == 1) {
                signUp();
            } else {
                logIn();
            }
        } while (user == -2);

        if (user == -1) {
            handleAdmin();
        } else {
            handleCustomer();
        }
    }

    private static void signUp() {
        int id = 0;

        if (!customers.isEmpty()) {
            id = customers.get(customers.size() - 1).getID();

            ++id;
        }

        System.out.println("\nCustomer ID: " + id);

        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter email: ");
        String email = input.nextLine();

        System.out.print("Enter phone number: ");
        String phone = input.nextLine();

        String password = getValidPassword(null);

        customers.add(new Customer(id, name, email, phone, password));

        user = id;
    }

    private static String getValidPassword(String currentPassword) {
        boolean valid;

        String password;

        while (true) {
            valid = true;

            System.out.print("Enter password: ");
            password = input.nextLine();

            if (password.isEmpty()) {
                System.out.println("Empty input!");

                continue;
            } else if (currentPassword != null && currentPassword.equals(password)) {
                return password;
            }

            for (Customer customer : customers) {
                if (customer.getPassword().equals(password)) {
                    valid = false;

                    break;
                }
            }

            if (valid) {
                return password;
            } else {
                System.out.println("Password already chosen!");
            }
        }
    }

    private static void logIn() {
        System.out.print("\nEnter your id: ");
        int id = getNumber(0, customers.get(customers.size() - 1).getID());

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        if (id == -1 && password.equals("admin")) {
            user = id;

            return;
        } else {
            for (Customer customer : customers) {
                if (customer.verifyIdentity(id, password)) {
                    user = customer.getID();

                    return;
                }
            }
        }

        System.out.println("Invalid username or password!");
    }

    private static int getAdminChoice() {
        System.out.println("\t\t\t\tWelcome to the Admin Interface\n\n");
        System.out.println("Do you want to:-\n[1] Add customer account.\n[2] Edit customer account.");
        System.out.println("[3] Delete customer account.\n[4] Display customer account.");
        System.out.println("[5] Add tour guide account.\n[6] Edit tour guide account.");
        System.out.println("[7] Delete tour guide account.\n[8] Display tour guide account.");
        System.out.println("[9] Add a trip.\n[10] Display a trip.\n");
        System.out.print("Enter your choice: ");

        return getNumber(1, 10);
    }

    private static int getCustomerChoice() {
        System.out.println("\t\t\t\tWelcome to the Customer Interface\n\n");
        System.out.println("Do you want to:-\n[1] Display account.\n[2] Edit personal information.");
        System.out.println("[3] Edit travel history.\n[4] Book a trip.\n[5]Display a ticket's information.");
        System.out.println("[6] Reschedule a booked trip.\n[7] Cancel a booked trip.");
        System.out.print("Enter your choice: ");

        return getNumber(1, 7);
    }

    private static void handleCustomer() {
        do {
            switch (getCustomerChoice()) {
                case 1:
                    customers.get(getCustomerIndex()).displayCustomer();

                    break;
                case 2:
                    editCustomer(getCustomerIndex());

                    break;
                case 3:
                    // travel history

                    break;
                case 4:
                    book();

                    break;
                case 5:
                    //display ticket

                    break;
                case 6:
                    //reschedule ticket

                    break;
                case 7:
                    //cancel ticket

                    break;
            }

            System.out.print("Press (Y) to continue or any other key to exit: ");
        } while (input.nextLine().equalsIgnoreCase("y"));
    }

    public static int getCustomerIndex() {
        for (int i = 0; i < customers.size(); ++i) {
            if (customers.get(i).getID() == user) {
                return i;
            }
        }

        return -1;
    }

    private static void handleAdmin() {
        do {
            switch (getAdminChoice()) {
                case 1:
                    signUp(); // add customer account

                    user = -1;

                    break;
                case 2:
                    editCustomer(selectCustomer());

                    break;
                case 3:
                    // deleteCustomer(); // reset trip seats

                    break;
                case 4:
                    customers.get(selectCustomer()).displayCustomer();

                    break;
                case 5:
                    addTourGuide();

                    break;
                case 6:
                    editTourGuide(selectTourGuide());

                    break;
                case 7:
                    int index = selectTourGuide();

                    tourGuides.remove(index);

                    break;
                case 8:
                    tourGuides.get(selectCustomer()).displayTourGuide();

                    break;
                case 9:
                    addTrip();

                    break;
                case 10:
                    trips.get(selectTrip()).displayTrip();

                    break;
            }

            System.out.print("Press (Y) to continue or any other key to exit: ");
        } while (input.nextLine().equalsIgnoreCase("y"));
    }

    private static void addTourGuide() {
        int id = 0;

        if (!tourGuides.isEmpty()) {
            id = tourGuides.get(tourGuides.size() - 1).getID();

            ++id;
        }

        System.out.println("\nTour Guide ID: " + id);

        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter phone number: ");
        String phone = input.nextLine();

        tourGuides.add(new TourGuide(id, name, phone));
    }

    private static int selectTourGuide() {
        if (tourGuides.isEmpty()) {
            System.out.println("There are no tour guides!");
        }

        System.out.println("\nSelect a tour guide:-");

        for (int i = 1; i <= tourGuides.size(); ++i) {
            System.out.println("[" + i + "] Tour Guide ID: " + tourGuides.get(i - 1).getID());
            System.out.println("\s\s\s\sTour Guide Name: " + tourGuides.get(i - 1).getName());
        }

        System.out.print("\nEnter your choice: ");

        return getNumber(1, tourGuides.size()) - 1;
    }

    private static void editTourGuide(int index) {
        tourGuides.get(index).displayTourGuide();

        System.out.println("\nEdit Tou Guide Information (Re-enter info you don't want to change):-");

        System.out.print("Enter name: ");
        tourGuides.get(index).setName(input.nextLine());

        System.out.print("Enter phone number: ");
        tourGuides.get(index).setPhone(input.nextLine());
    }

    private static void addTrip() {
        int id = 0;
        LocalDate endDate;

        if (!trips.isEmpty()) {
            id = trips.get(trips.size() - 1).getID();

            ++id;
        }

        System.out.println("\nTrip ID: " + id);

        System.out.print("Enter Main Tour: ");
        String mainTour = input.nextLine();

        System.out.print("Enter Seat Price: ");
        int seatPrice = getNumber(100, 500);

        System.out.print("Enter Number Of Seats: ");
        int numberOfSeats = getNumber(10, 50);

        System.out.println("Enter Start Date:-");
        LocalDate startDate = getDate();

        while (true) {
            System.out.println("Enter End Date:-");
            endDate = getDate();

            if (!endDate.isBefore(startDate)) {
                break;
            }

            System.out.println("Error: End date is before start date");
        }

        while (true) {
            System.out.println("Enter Trip Type ('g' for general - 'c' couple - 'f' family): ");
            switch (input.nextLine().toLowerCase()) {
                case "g":
                    trips.add(new GeneralTrip(id, mainTour, seatPrice, numberOfSeats, endDate, startDate));

                    break;
                case "c":
                    trips.add(new CoupleTrip(id, mainTour, seatPrice, numberOfSeats, endDate, startDate));

                    break;
                case "f":
                    trips.add(new FamilyTrip(id, mainTour, seatPrice, numberOfSeats, endDate, startDate));

                    break;
                default:
                    System.out.println("Error: Invalid choice!");

                    continue;
            }

            break;
        }

        trips.get(trips.size() - 1).addHotels();
        trips.get(trips.size() - 1).addFlights();
        trips.get(trips.size() - 1).addCarRentals();
        trips.get(trips.size() - 1).addActivities();
    }

    private static int selectTrip() {
        if (trips.isEmpty()) {
            System.out.println("There are no trips!");
        }

        System.out.println("\nSelect a trip:-");

        for (int i = 1; i <= trips.size(); ++i) {
            System.out.println("[" + i + "] Trip ID: " + trips.get(i - 1).getID());
            System.out.println("\s\s\s\sMain tour: " + trips.get(i - 1).getMainTour());
        }

        System.out.print("\nEnter your choice: ");

        return getNumber(1, trips.size()) - 1;
    }

    private static int selectCustomer() {
        if (customers.isEmpty()) {
            System.out.println("There are no customers!");
        }

        System.out.println("\nSelect a customer:-");

        for (int i = 1; i <= customers.size(); ++i) {
            System.out.println("[" + i + "] Customer ID: " + customers.get(i - 1).getID());
            System.out.println("\s\s\s\sCustomer Name: " + customers.get(i - 1).getName());
        }

        System.out.print("\nEnter your choice: ");

        return getNumber(1, customers.size()) - 1;
    }

    private static void editCustomer(int index) {
        customers.get(index).displayCustomer();

        System.out.println("\nEdit Customer Information (Re-enter info you don't want to change):-");

        System.out.print("Enter name: ");
        customers.get(index).setName(input.nextLine());

        System.out.print("Enter email: ");
        customers.get(index).setEmail(input.nextLine());

        System.out.print("Enter phone number: ");
        customers.get(index).setPhone(input.nextLine());

        customers.get(index).setPassword(getValidPassword(customers.get(index).getPassword()));
    }

    private static int welcomeUser() {
        System.out.println("\t\t\t\tWelcome to the Travel Management System\n\n");
        System.out.println("Do you want to:-\n[1] Sign Up.\n[2] Log In.\n");
        System.out.print("Enter your choice: ");

        return getNumber(1, 2);
    }

    private static int getNumber(int lowerBound, int upperBound) {
        int number = 0;

        while (true) {
            try {
                while (true) {
                    number = input.nextInt();

                    input.nextLine();

                    if (number >= lowerBound && number <= upperBound) {
                        return number;
                    }

                    System.out.print("Please, enter a valid option options [" + lowerBound + " - " + upperBound + "]: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Please, enter a number: ");

                input.nextLine();
            }
        }
    }

    private static LocalDate getDate() {
        LocalDate date;
        int year = 0;
        int month = 0;
        int day = 0;

        while (true) {
            System.out.print("Enter Year: ");
            year = getNumber(LocalDate.now().getYear(), LocalDate.now().getYear() + 10);

            System.out.print("Enter Month: ");
            month = getNumber(1, 12);

            System.out.print("Enter Day: ");
            day = getNumber(1, 31);

            try {
                date = LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                System.out.println("Error: Invalid date!");

                continue;
            }

            if (date.isAfter(LocalDate.now())) {
                return date;
            }

            System.out.println("Error: Entered date is before the current date");
        }
    }

    private static void book() {
        String tripChoice;

        System.out.print("Enter number of seats to book (1 -> 10): ");

        int bookedSeats = getNumber(1, 10);

        if (bookedSeats == 2) {
            System.out.println("\nAvailable trip types:-");
            System.out.println("[G] General Trips.\n[C] Couple Trips.\n[F] Family Trips.");
            System.out.print("Enter your choice: ");

            while (true) {
                tripChoice = input.nextLine();

                if (tripChoice.equalsIgnoreCase("g") || tripChoice.equalsIgnoreCase("c") ||
                        tripChoice.equalsIgnoreCase("f")) {
                    break;
                }

                System.out.print("Please, enter a valid choice (g - c - f): ");
            }
        } else if (bookedSeats > 2) {
            System.out.println("\nAvailable trip types:-");
            System.out.println("[G] General Trips.\n[F] Family Trips.");
            System.out.print("Enter your choice: ");

            while (true) {
                tripChoice = input.nextLine();

                if (tripChoice.equalsIgnoreCase("g") || tripChoice.equalsIgnoreCase("f")) {
                    break;
                }

                System.out.print("Please, enter a valid choice (g - c - f): ");
            }
        } else {
            tripChoice = "g";
        }

        System.out.println("\nAvailable Ticket types:-");
        System.out.println("[1] Silver Ticket: Main Tour + 1 Main Feature.");
        System.out.println("[2] Gold Ticket: Main Tour + 2 Main Feature + 5% Voucher for your next trip.");
        System.out.println("[3] Platinum Ticket: Main Tour + 3 Main Feature + 10% Voucher for your next trip + " +
                "Free Car Rental.");
        System.out.print("Enter your choice: ");

        int ticketChoice = getNumber(1, 3);
    }

    private static int bookTrip(String tripChoice, int bookedSeats) {
        String selectedTrip;

        ArrayList<String> displayedTrips = new ArrayList<>();

        System.out.println("Available Trips:-");

        for (Trip trip : trips) {
            if (tripChoice.equalsIgnoreCase("g") && trip instanceof GeneralTrip) {
                System.out.println("Main Tour: " + trip.getMainTour() + " | ID: " + trip.getID() + " | Seat Price: " +
                        trip.getSeatPrice() + " | Start Date: " + trip.getStartDate() + " | End Date: " +
                        trip.getEndDate());

                displayedTrips.add(Integer.toString(trip.getID()));
            } else if (tripChoice.equalsIgnoreCase("c") && trip instanceof CoupleTrip) {
                System.out.println("Main Tour: " + trip.getMainTour() + " | ID: " + trip.getID() + " | Seat Price: " +
                        trip.getSeatPrice() + " | Start Date: " + trip.getStartDate() + " | End Date: " +
                        trip.getEndDate());

                displayedTrips.add(Integer.toString(trip.getID()));
            } else if (tripChoice.equalsIgnoreCase("f") && trip instanceof FamilyTrip) {
                System.out.println("Main Tour: " + trip.getMainTour() + " | ID: " + trip.getID() + " | Seat Price: " +
                        trip.getSeatPrice() + " | Start Date: " + trip.getStartDate() + " | End Date: " +
                        trip.getEndDate());

                displayedTrips.add(Integer.toString(trip.getID()));
            }
        }

        System.out.print("Enter your choice: ");

        while (true) {
            selectedTrip = Integer.toString(getNumber(0, trips.get(trips.size() - 1).getID()));

            if (displayedTrips.contains(selectedTrip)) {
                return Integer.parseInt(selectedTrip);
            }

            System.out.print("Please, enter a valid choice: ");
        }
    }
}
