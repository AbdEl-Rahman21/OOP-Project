package Account;

import java.util.ArrayList;

import java.time.LocalDate;

public class TourGuide {
    private final int ID;

    private String name;
    private String assignedTrip;

    private static int availableGuides = 0;

    private ArrayList<LocalDate> tripsMade = new ArrayList<>();

    public TourGuide(int id, String name) {
        this.ID = id;
        this.name = name;

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

    public String getAssignedTrip() {
        return assignedTrip;
    }

    public void setAssignTrip(String assignedTrip) {
        if (assignedTrip == null && this.assignedTrip != null) {
            ++availableGuides;
        } else if (assignedTrip != null && this.assignedTrip == null) {
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
        this.tripsMade = tripsMade;
    }

    public void finishTrip(LocalDate tripDate) {
        setAssignTrip(null);

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
        System.out.println("Assigned trip: " + (assignedTrip == null ? "Available" : assignedTrip));
        System.out.println("Last Month Salary: " + calculateSalary());
    }
}
