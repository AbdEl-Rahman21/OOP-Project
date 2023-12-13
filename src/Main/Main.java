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
    private static int user = -2;   // -1 for admin or customer index
    private static int lastTicketId = 0; // Stored to generate new IDs

    private final static Scanner input = new Scanner(System.in);

    private final static ArrayList<Trip> trips = new ArrayList<>();
    private final static ArrayList<Customer> customers = new ArrayList<>();
    private final static ArrayList<TourGuide> tourGuides = new ArrayList<>();

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

    private static int welcomeUser() {
        System.out.println("\t\t\t\tWelcome to the Travel Management System\n\n");
        System.out.println("Do you want to:-\n[1] Sign Up.\n[2] Log In.\n");
        System.out.print("Enter your choice: ");

        return getNumber(1, 2);
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

        user = customers.size() - 1;
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
                    user = customers.indexOf(customer);

                    return;
                }
            }
        }

        System.out.println("Invalid username or password!");
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

    private static void handleCustomer() {
        do {
            switch (getCustomerChoice()) {
                case 1:
                    customers.get(user).displayCustomer();

                    break;
                case 2:
                    editCustomer(user);

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

    private static int getCustomerChoice() {
        System.out.println("\t\t\t\tWelcome to the Customer Interface\n\n");
        System.out.println("Do you want to:-\n[1] Display account.\n[2] Edit personal information.");
        System.out.println("[3] Edit travel history.\n[4] Book a trip.\n[5]Display a ticket's information.");
        System.out.println("[6] Reschedule a booked trip.\n[7] Cancel a booked trip.");
        System.out.print("Enter your choice: ");

        return getNumber(1, 7);
    }

    private static void book() {
        System.out.print("Enter number of seats to book (1 -> 10): ");

        int bookedSeats = getNumber(1, 10);

        int tripChoice = getTripTypeChoice(bookedSeats);    // 1 for General 2 for Family 3 for Couple

        int ticketChoice = getTicketChoice();   // 1 for Silver 2 for Gold 3 for Platinum

        int tripIndex = bookTrip(tripChoice, bookedSeats);

        Ticket ticket = createTicket(bookedSeats, ticketChoice, tripIndex);

        handlePayment(bookedSeats, tripIndex, ticket);

        customers.get(user).addTicket(ticket);
    }

    private static int getTripTypeChoice(int bookedSeats) {
        if (bookedSeats == 2) {
            System.out.println("\nAvailable trip types:-");
            System.out.println("[1] General Trips.\n[2] Family Trips.\n[3] Couple Trips.");
            System.out.print("Enter your choice: ");

            return getNumber(1, 3);
        } else if (bookedSeats > 2) {
            System.out.println("\nAvailable trip types:-");
            System.out.println("[1] General Trips.\n[2] Family Trips.");
            System.out.print("Enter your choice: ");

            return getNumber(1, 2);
        }

        return 1;
    }

    private static int getTicketChoice() {
        System.out.println("\nAvailable Ticket types:-");
        System.out.println("[1] Silver Ticket: Main Tour + 1 Main Feature.");
        System.out.println("[2] Gold Ticket: Main Tour + 2 Main Feature + 5% Voucher for your next trip.");
        System.out.println("[3] Platinum Ticket: Main Tour + 3 Main Feature + 10% Voucher for your next trip + " +
                "Free Car Rental.");
        System.out.print("Enter your choice: ");

        return getNumber(1, 3);
    }

    private static int bookTrip(int tripChoice, int bookedSeats) {
        String selectedTrip;

        ArrayList<String> displayedTrips = new ArrayList<>();

        System.out.println("\nAvailable Trips:-");

        for (Trip trip : trips) {
            if (trip.availableTrip(bookedSeats)) {
                if (tripChoice == 1 && trip instanceof GeneralTrip) {
                    System.out.println("Main Tour: " + trip.getMainTour() + " | ID: " + trip.getID() +
                            " | Seat Price: " + trip.getSeatPrice() + " | Start Date: " + trip.getStartDate() +
                            " | End Date: " + trip.getEndDate());

                    displayedTrips.add(Integer.toString(trip.getID()));
                } else if (tripChoice == 2 && trip instanceof FamilyTrip) {
                    System.out.println("Main Tour: " + trip.getMainTour() + " | ID: " + trip.getID() +
                            " | Seat Price: " + trip.getSeatPrice() + " | Start Date: " + trip.getStartDate() +
                            " | End Date: " + trip.getEndDate());

                    displayedTrips.add(Integer.toString(trip.getID()));
                } else if (tripChoice == 3 && trip instanceof CoupleTrip) {
                    System.out.println("Main Tour: " + trip.getMainTour() + " | ID: " + trip.getID() +
                            " | Seat Price: " + trip.getSeatPrice() + " | Start Date: " + trip.getStartDate() +
                            " | End Date: " + trip.getEndDate());

                    displayedTrips.add(Integer.toString(trip.getID()));
                }
            }
        }

        System.out.print("Enter your choice: ");

        while (true) {
            selectedTrip = Integer.toString(getNumber(0, trips.get(trips.size() - 1).getID()));

            if (displayedTrips.contains(selectedTrip)) {
                for (Trip trip : trips) {
                    if (trip.getID() == Integer.parseInt(selectedTrip)) {
                        return trips.indexOf(trip);
                    }
                }
            }

            System.out.print("Please, enter a valid choice: ");
        }
    }

    private static Ticket createTicket(int bookedSeats, int ticketChoice, int tripIndex) {
        int choice;

        Ticket ticket = switch (ticketChoice) {
            case 1 -> new SilverTicket(lastTicketId + 1, trips.get(tripIndex).getID(), bookedSeats,
                    getTripMainFeature(ticketChoice, tripIndex)[0]);
            case 2 -> new GoldTicket(lastTicketId + 1, trips.get(tripIndex).getID(), bookedSeats,
                    getTripMainFeature(ticketChoice, tripIndex));
            case 3 -> new PlatinumTicket(lastTicketId + 1, trips.get(tripIndex).getID(), bookedSeats,
                    getTripMainFeature(ticketChoice, tripIndex));
            default -> null;
        };

        System.out.print("Do you want to book a hotel (Y / N): ");

        if (input.nextLine().equalsIgnoreCase("y")) {
            trips.get(tripIndex).displayHotels();

            System.out.print("Enter your choice: ");

            choice = getNumber(1, trips.get(tripIndex).getHotels().size());

            ticket.setBookedHotel(trips.get(tripIndex).getHotels().get(choice - 1));
        }

        System.out.print("Do you want to book a flight (Y / N): ");

        if (input.nextLine().equalsIgnoreCase("y")) {
            trips.get(tripIndex).displayFlights();

            System.out.print("Enter your choice: ");

            choice = getNumber(1, trips.get(tripIndex).getFlights().size());

            ticket.setBookedFlight(trips.get(tripIndex).getFlights().get(choice - 1));
        }

        if (ticketChoice != 3) {
            System.out.print("Do you want to book a Car Rental (Y / N): ");

            if (input.nextLine().equalsIgnoreCase("y")) {
                trips.get(tripIndex).displayCarRentals();

                System.out.print("Enter your choice: ");

                choice = getNumber(1, trips.get(tripIndex).getCarRentals().size());

                ticket.setBookedCarRental(trips.get(tripIndex).getCarRentals().get(choice - 1));
            }
        }

        System.out.print("Do you want to book activities (Y / N): ");

        if (input.nextLine().equalsIgnoreCase("y")) {
            do {
                trips.get(tripIndex).displayActivities();

                System.out.print("Enter your choice: ");

                choice = getNumber(1, trips.get(tripIndex).getActivities().size());

                ticket.addActivity(trips.get(tripIndex).getActivities().get(choice - 1));

                System.out.print("Do you want to book another activity (Y / N): ");
            } while (input.nextLine().equalsIgnoreCase("y"));
        }

        ++lastTicketId;

        return ticket;
    }

    private static String[] getTripMainFeature(int ticket, int index) {
        String[] mainFeatures;

        if (trips.get(index) instanceof GeneralTrip) {
            mainFeatures = GeneralTrip.getMainFeatures();
        } else if (trips.get(index) instanceof FamilyTrip) {
            mainFeatures = FamilyTrip.getMainFeatures();
        } else {
            mainFeatures = CoupleTrip.getMainFeatures();
        }

        if (ticket == 2) {
            return new String[]{mainFeatures[0], mainFeatures[1]};
        }

        return mainFeatures;
    }

    private static void handlePayment(int bookedSeats, int tripIndex, Ticket ticket) {
        float price = 0f;

        price += trips.get(tripIndex).getSeatPrice() * bookedSeats;

        if (ticket instanceof SilverTicket) {
            price += 100;
        } else if (ticket instanceof GoldTicket) {
            price += 250;
        } else {
            price += 400;
        }

        if (!ticket.getBookedHotel().isEmpty()) {
            price += 100;
        }

        if (!ticket.getBookedFlight().isEmpty()) {
            price += 100;
        }

        if (!ticket.getBookedCarRental().isEmpty() && !(ticket instanceof PlatinumTicket)) {
            price += 100;
        }

        if (!ticket.getActivities().isEmpty()) {
            price += ticket.getActivities().size() * 50;
        }

        // voucher

        // discount

        System.out.println("Please pay: " + price);
    }

    // Utility Methods
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
}
