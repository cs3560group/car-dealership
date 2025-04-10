package com.dealership;

//imports needed for the program
import com.dealership.models.Customer;
import com.dealership.models.Vehicle;
import com.dealership.models.UsedVehicle;
import com.dealership.Inventory;

public class Main {
    public static void main(String[] args) throws Exception {
        //Creating customer object
        Customer customer = new Customer("John", "1234567890", "JohnSDas@gmail.com", "1234");
        customer.printCustomerInfo();

        System.out.println("---------------------------------");

        //creating an inventory and adding used vehciles to it 
        Inventory inventory = new Inventory(5); //thresholdLimit is 5 

        //creating used vehicle objects to add into the inventory
        Vehicle car1 = new UsedVehicle("1HGCM82633A123456", "Honda", "Accord", 2020, 20000, "available", "good", 30000, 1);
        Vehicle car2 = new UsedVehicle("1HGCM82633A123457", "Toyota", "Camry", 2019, 18000, "available", "great", 25000, 2);

        inventory.addVehicle(car1);
        inventory.addVehicle(car2);

        // Display current inventory
        System.out.println("Available vehicles:");
            for (Vehicle v : inventory.getAvailableVehicles()) {
                v.displayInfo();
                System.out.println();
            }
        
        System.out.println("------------------");
    }
}
