package Trips;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Trip {
    final int ID;

    String mainTour;

    int seatPrice;
    int numberOfSeats;
    int numberOfBookedSeats;

    LocalDate endDate;
    LocalDate startDate;

    final ArrayList<String> hotels = new ArrayList<>();
    final ArrayList<String> flights = new ArrayList<>();
    final ArrayList<String> carRentals = new ArrayList<>();
    final ArrayList<String> activities = new ArrayList<>();

    private final static Scanner INPUT = new Scanner(System.in);

    public Trip(int id, String mainTour, int seatPrice, int numberOfSeats, LocalDate endDate, LocalDate startDate) {
        this.ID = id;
        this.mainTour = mainTour;
        this.seatPrice = seatPrice;
        this.numberOfSeats = numberOfSeats;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public int getID() {
        return ID;
    }

    public String getMainTour() {
        return mainTour;
    }

    public void setMainTour(String mainTour) {
        this.mainTour = mainTour;
    }

    public int getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(int seatPrice) {
        this.seatPrice = seatPrice;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfBookedSeats() {
        return numberOfBookedSeats;
    }

    public void setNumberOfBookedSeats(int numberOfBookedSeats) {
        this.numberOfBookedSeats = numberOfBookedSeats;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void displayTripDuration() {
        System.out.println("Trip duration in days: " + startDate.until(endDate, ChronoUnit.DAYS));
    }

    public ArrayList<String> getHotels() {
        return hotels;
    }

    public void setHotels(ArrayList<String> hotels) {
        this.hotels.addAll(hotels);
    }

    public void addHotels() {
        String hotel;

        do {
            System.out.print("\nEnter hotel: ");
            hotel = INPUT.nextLine();

            if (hotels.contains(hotel)) {
                System.out.println("Hotel already exists!");
            } else if (!hotel.isEmpty()) {
                hotels.add(hotel);
            }

            System.out.print("Press (Y) to add another hotel or any other key to exit: ");
        } while (INPUT.nextLine().equalsIgnoreCase("y"));
    }

    public void displayHotels() {
        System.out.println("\nHotels:-");

        for (int i = 1; i <= hotels.size(); ++i) {
            System.out.println("[" + i + "] " + hotels.get(i - 1) + ".");
        }
    }

    public ArrayList<String> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<String> flights) {
        this.flights.addAll(flights);
    }

    public void addFlights() {
        String flight;

        do {
            System.out.print("\nEnter flight: ");
            flight = INPUT.nextLine();

            if (flights.contains(flight)) {
                System.out.println("Flight already exists!");
            } else if (!flight.isEmpty()) {
                flights.add(flight);
            }

            System.out.print("Press (Y) to add another flight or any other key to exit: ");
        } while (INPUT.nextLine().equalsIgnoreCase("y"));
    }

    public void displayFlights() {
        System.out.println("\nFlights:-");

        for (int i = 1; i <= flights.size(); ++i) {
            System.out.println("[" + i + "] " + flights.get(i - 1) + ".");
        }
    }

    public ArrayList<String> getCarRentals() {
        return carRentals;
    }

    public void setCarRentals(ArrayList<String> carRentals) {
        this.carRentals.addAll(carRentals);
    }

    public void addCarRentals() {
        String carRental;

        do {
            System.out.print("\nEnter car rental: ");
            carRental = INPUT.nextLine();

            if (carRentals.contains(carRental)) {
                System.out.println("Car rental already exists!");
            } else if (!carRental.isEmpty()) {
                carRentals.add(carRental);
            }

            System.out.print("Press (Y) to add another car rental or any other key to exit: ");
        } while (INPUT.nextLine().equalsIgnoreCase("y"));
    }

    public void displayCarRentals() {
        System.out.println("\nCar Rentals:-");

        for (int i = 1; i <= carRentals.size(); ++i) {
            System.out.println("[" + i + "] " + carRentals.get(i - 1) + ".");
        }
    }

    public ArrayList<String> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<String> activities) {
        this.activities.addAll(activities);
    }

    public void addActivities() {
        String activity;

        do {
            System.out.print("\nEnter activity: ");
            activity = INPUT.nextLine();

            if (activities.contains(activity)) {
                System.out.println("Activity already exists!");
            } else if (!activity.isEmpty()) {
                activities.add(activity);
            }

            System.out.print("Press (Y) to add another activity or any other key to exit: ");
        } while (INPUT.nextLine().equalsIgnoreCase("y"));
    }

    public void displayActivities() {
        System.out.println("\nActivities:-");

        for (int i = 1; i <= activities.size(); ++i) {
            System.out.println("[" + i + "] " + activities.get(i - 1) + ".");
        }
    }

    public boolean availableTrip(int bookedSeats) {
        return bookedSeats <= (numberOfSeats - numberOfBookedSeats) && startDate.isAfter(LocalDate.now());
    }

    public abstract void displayTrip();
}
