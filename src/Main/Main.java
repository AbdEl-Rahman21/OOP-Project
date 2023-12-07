package Main;

import Trips.*;

import Account.Customer;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    // Array of tour guides
    private final static ArrayList<Trip> trips = new ArrayList<>();
    private final static ArrayList<Customer> customers = new ArrayList<>();
    private static int user = -2;  // -1 for admin or customer id
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
            id = Integer.parseInt(customers.get(customers.size()-1).getId());

            ++id;
        }

        System.out.println("Customer ID: " + id);

        System.out.print("Enter name: ");
        String name = input.next();

        System.out.print("Enter email: ");
        String email = input.next();

        System.out.print("Enter phone number: ");
        String phone = input.next();

        String password = getValidPassword(null);

        customers.add(new Customer(Integer.toString(id), name, email, phone, password));

        user = id;
    }

    private static String getValidPassword(String currentPassword) {
        String password;
        boolean valid;

        while (true) {
            valid = true;

            System.out.print("Enter password: ");
            password = input.next();

            if (currentPassword != null && currentPassword.equals(password)) {
                return password;
            }

            for (Customer customer: customers) {
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
        String id = input.next();

        System.out.print("Enter your password: ");
        String password = input.next();

        if (id.equals("admin") && password.equals("admin")) {
            user = -1;

            return;
        } else {
            for (Customer customer: customers) {
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
                    //addTrip();

                    break;
                case 10:
                    //displayTrip();

                    break;
            }

            System.out.print("Press (Y) to continue or any other key to exit: ");
        } while (input.next().equalsIgnoreCase("y"));
    }

    private static int selectCustomer() {
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

        System.out.println("Edit Customer Information (Re-enter info you don't want to change):-");

        System.out.print("Enter name: ");
        customers.get(index).setName(input.next());

        System.out.print("Enter email: ");
        customers.get(index).setEmail(input.next());

        System.out.print("Enter phone number: ");
        customers.get(index).setPhone(input.next());

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

                    if (number >= lowerBound && number <= upperBound) {
                        return number;
                    }

                    System.out.print("Please, enter a valid option options: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Please, enter a number: ");

                input.next();
            }
        }
    }
}
