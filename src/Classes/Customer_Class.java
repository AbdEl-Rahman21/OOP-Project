package Classes;

public class Customer_Class
{
    //Attributes
    private int Customer_id;
    private int Numof_trips;
    private String Customer_name;
    private int birth_date;
    private String Customer_email;
    private int Password;
    private int Customer_phone;
    //private booked_tickets[];

    public Customer_Class(String customer_name, int birth_date, String customer_email, int customer_phone)
    {
        Customer_name = customer_name;
        this.birth_date = birth_date;
        Customer_email = customer_email;
        Customer_phone = customer_phone;

    }
    //Getters and Setters
    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String customer_name) {
        Customer_name = customer_name;
    }

    public int getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(int birth_date) {
        this.birth_date = birth_date;
    }

    public String getCustomer_email() {
        return Customer_email;
    }

    public void setCustomer_email(String customer_email) {
        Customer_email = customer_email;
    }

    public int getCustomer_phone() {
        return Customer_phone;
    }

    public void setCustomer_phone(int customer_phone) {
        Customer_phone = customer_phone;
    }

    public int getPassword() {
        return Password;
    }

    public void setPassword(int password) {
        Password = password;
    }
    // Methods

}
