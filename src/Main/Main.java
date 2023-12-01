package Main;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class Main {
    // Array of tour guides
    // Array of trips
    // Array of Customers
    private static String user = "\0"; // admin or index
    private final static Scanner input = new Scanner(System.in);
    private static HashMap<String, String> reservation = new HashMap<String, String>();

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
                //signUp();
            } else {
                logIn();
            }
        } while (user.equals("\0"));
    }

    //private static void signUp() {}

    private static void logIn() {
        String username;
        String password;

        System.out.print("\nEnter your username: ");
        username = input.next();

        System.out.print("Enter your password: ");
        password = input.next();

        if (username.equals("admin") && password.equals("admin")) {
            user = "admin";

            return;
        } /*else {
            for (int i = 0; i < customers.size; ++i) {
                if (username.equals(customer.getId()) && password.equals(customer.getPassword())) {
                    user = Integer.toString(i);

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

                    System.out.print("Please, enter one of the available options: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Please, enter a number: ");

                input.next();
            }
        }
    }
}
