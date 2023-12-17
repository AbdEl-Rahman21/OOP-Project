package Account;

import java.util.ArrayList;

import java.time.LocalDate;

public class TourGuide {
    private final int ID;

    private String name;
    private String phone;

    int assignedTrip = -1;

    private static int availableGuides = 0;

    final private ArrayList<LocalDate> tripsMade = new ArrayList<>();

    public TourGuide(int id, String name, String phone) {
        this.ID = id;
        this.name = name;
        this.phone = phone;

        ++availableGuides;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAssignedTrip() {
        return assignedTrip;
    }

    public void setAssignedTrip(int assignedTrip) {
        if (assignedTrip == -1 && this.assignedTrip != -1) {
            ++availableGuides;
        } else if (assignedTrip != -1 && this.assignedTrip == -1) {
            --availableGuides;
        }

        this.assignedTrip = assignedTrip;
    }

    public static int getAvailableGuides() {
        return availableGuides;
    }

    public static void setAvailableGuides(int availableGuides) {
        TourGuide.availableGuides = availableGuides;
    }

    public ArrayList<LocalDate> getTripsMade() {
        return tripsMade;
    }

    public void setTripsMade(ArrayList<LocalDate> tripsMade) {
        this.tripsMade.addAll(tripsMade);
    }

    public void finishTrip(LocalDate tripDate) {
        setAssignedTrip(-1);

        tripsMade.add(tripDate);

        ++availableGuides;
    }

    public int calculateSalary() {
        int counter = 0;
        int dateDifference = 0;

        for (LocalDate tripDate : getTripsMade()) {
            dateDifference = LocalDate.now().getMonth().compareTo(tripDate.getMonth());

            if (dateDifference == 1 || dateDifference == -11) {
                ++counter;
            }
        }

        return counter * 500;
    }

    public void displayTourGuide() {
        System.out.println("\nTour Guide Information:-");
        System.out.println("Id: " + getID());
        System.out.println("Name: " + getName());
        System.out.println("Phone Number: " + getPhone());
        System.out.println("Assigned trip: " + (getAssignedTrip() == -1 ? "Available" : getAssignedTrip()));
        System.out.println("Last Month Salary: " + calculateSalary());
    }
}
