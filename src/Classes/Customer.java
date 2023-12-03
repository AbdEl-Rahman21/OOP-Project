package Classes;

public class Customer {
    // Attributes
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private int numberOfTrips;
    // private int birth_date;
    // private booked_tickets[];

    public Customer(String id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public int getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(int birth_date) {
        this.birth_date = birth_date;
    }*/

    // Methods
    public boolean verifyIdentity(String id, String password) {
        return id.equals(this.id) && password.equals(this.password);
    }

    public void displayCustomer() {
        System.out.println("Id: " + id + "\nName: " + name + "\nEmail: " + email + "\nPhone: " + phone);
        System.out.println("Password: " + password + "\nNumber Of Trips: " + numberOfTrips);
    }
}
