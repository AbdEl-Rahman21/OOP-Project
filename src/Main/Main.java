package Main;

import Trips.*;

import Tickets.*;

import Account.Customer;

import java.time.LocalDate;
import java.time.DateTimeException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    // Array of tour guides
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
        }
    }

    private static void signUp() {
        int id = 0;

        if (!customers.isEmpty()) {
            id = Integer.parseInt(customers.get(customers.size() - 1).getId());

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

        customers.add(new Customer(Integer.toString(id), name, email, phone, password));

        user = id;
    }

    private static String getValidPassword(String currentPassword) {
        boolean valid;

        String password;

        while (true) {
            valid = true;

            System.out.print("Enter password: ");
            password = input.nextLine();

            if (currentPassword != null && currentPassword.equals(password)) {
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
        String id = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        if (id.equals("admin") && password.equals("admin")) {
            user = -1;

            return;
        } else {
            for (Customer customer : customers) {
                if (customer.verifyIdentity(id, password)) {
                    user = Integer.parseInt(customer.getId());

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

        return getNumber(1,10);
    }

    private static void handleAdmin() {
        do {
            switch (getAdminChoice()) {
                case 1:
                    signUp(); // add customer account

                    break;
                case 2:
                    editCustomer();

                    break;
                case 3:
                    // deleteCustomer(); // reset trip seats

                    break;
                case 4:
                    customers.get(selectCustomer()).displayCustomer();

                    break;
                case 5:
                    //addGuide();

                    break;
                case 6:
                    //editGuide();

                    break;
                case 7:
                    //deleteGuide();

                    break;
                case 8:
                    //displayGuide();

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

    private static void addTrip() {
        int id = 0;
        LocalDate endDate;

        if (!trips.isEmpty()) {
            id = Integer.parseInt(trips.get(trips.size() - 1).getId());

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
                    trips.add(new GeneralTrip(Integer.toString(id), mainTour, seatPrice, numberOfSeats, endDate,
                            startDate));

                    break;
                case "c":
                    trips.add(new CoupleTrip(Integer.toString(id), mainTour, seatPrice, numberOfSeats, endDate,
                            startDate));

                    break;
                case "f":
                    trips.add(new FamilyTrip(Integer.toString(id), mainTour, seatPrice, numberOfSeats, endDate,
                            startDate));

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
            System.out.println("[" + i + "] Trip ID: " + trips.get(i - 1).getId());
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
            System.out.println("[" + i + "] Customer ID: " + customers.get(i - 1).getId());
            System.out.println("\s\s\s\sCustomer Name: " + customers.get(i - 1).getName());
        }

        System.out.print("\nEnter your choice: ");

        return getNumber(1, customers.size()) - 1;
    }

    private static void editCustomer() {
        int index = selectCustomer();

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

        return getNumber(1,2);
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
}
