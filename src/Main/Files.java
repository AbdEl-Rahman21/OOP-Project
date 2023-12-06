package Main;

import Account.Customer;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Files {
    public File customersFile;

    public Files() {
        this.customersFile = new File("customer.txt");
    }

    public void writeTo(ArrayList<Customer> cust)
    {
        try {
            FileWriter myWriter = new FileWriter("customer.txt");
            for (int i = 0; i < 1; i++){  // number of customers needed
            myWriter.write(cust.get(i).getName() + "\n");
            myWriter.write( cust.get(i).getEmail()+ "\n");
            myWriter.write(cust.get(i).getPassword() + "\n");
            myWriter.write(cust.get(i).getPhone()+ "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFrom(ArrayList<Customer> cust) {
        try {
            File CustObj = new File("customer.txt");
            Scanner myReader = new Scanner(CustObj);
            while (myReader.hasNextLine()) {
                for (int i = 0; i < 1; i++) {      // number of customers needed
                    cust.get(i).setName( myReader.nextLine());
                    cust.get(i).setEmail( myReader.nextLine());
                    cust.get(i).setPassword(myReader.nextLine());
                    cust.get(i).setPhone( myReader.nextLine());
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
