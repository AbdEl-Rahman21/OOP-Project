package Main;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.InputMismatchException;
import Classes.Customer;

public class Main {
    // Array of tour guides
    // Array of trips
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static int user = -2;  // -1 for admin or customer index
    private final static Scanner input = new Scanner(System.in);
    private static HashMap<String, String> reservation = new HashMap<>();

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

        /*if (user == -1) {
            handleAdmin();
        } else {

        }*/
    }

    private static void signUp() {
        int id = 0;

        if (!customers.isEmpty()) {
            id = Integer.parseInt(customers.getLast().getId());

            ++id;
        }

        System.out.println("Customer ID: " + id);

        System.out.print("Enter name: ");
        String name = input.next();

        System.out.print("Enter email: ");
        String email = input.next();

        System.out.print("Enter phone number: ");
        String phone = input.next();

        String password = getValidPassword();

        customers.add(new Customer(Integer.toString(id), name, email, phone, password));

        user = id;
    }

    private static String getValidPassword() {
        String password;
        boolean valid;

        while (true) {
            valid = true;

            System.out.print("Enter password: ");
            password = input.next();

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
        } /*else {
            for (Customer customer: customers) {
                if (customer.verifyIdentity(id, password)) {
                    user = Integer.parseInt(customer.getId());

                    return;
                }
            }
        }*/

        System.out.println("Invalid username or password!");
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
