package Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class files {
   public File customersFile;

    public files()
    {
        this.customersFile = new File("customer.txt");
    }

    public void writeTo(ArrayList<Customer_Class> cust)
    {
        try {
            FileWriter myWriter = new FileWriter("customer.txt");
            for (int i = 0; i < 1; i++){  // number of customers needed
            myWriter.write(cust.get(i).getCustomer_name() + "\n");
            myWriter.write( cust.get(i).getCustomer_email()+ "\n");
            myWriter.write(Integer.toString(cust.get(i).getPassword()));
            myWriter.write("\n");
            myWriter.write(Integer.toString(cust.get(i).getBirth_date()));
            myWriter.write("\n");
            myWriter.write(Integer.toString(cust.get(i).getCustomer_phone()));
            myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFrom(ArrayList<Customer_Class> cust) {
        try {
            File CustObj = new File("customer.txt");
            Scanner myReader = new Scanner(CustObj);
            while (myReader.hasNextLine()) {
                for (int i = 0; i < 1; i++) {      // number of customers needed
                    cust.get(i).setCustomer_name( myReader.nextLine());
                    cust.get(i).setCustomer_email( myReader.nextLine());
                    cust.get(i).setPassword( Integer.valueOf(myReader.nextLine()));
                    cust.get(i).setBirth_date( Integer.valueOf(myReader.nextLine()));
                    cust.get(i).setCustomer_phone( Integer.valueOf(myReader.nextLine()));
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
