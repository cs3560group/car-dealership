package com.dealership.models;

public class UsedVehicle extends Vehicle {

    // UsedVehicle class with mileage and previousOwners attributes
    private int mileage;
    private int previousOwners;

    // Constructor
    public UsedVehicle(String vin, String make, String model, int year, double price, String status, String condition,
            int mileage, int previousOwners) {
        super(vin, make, model, year, price, status, condition);
        this.mileage = mileage;
        this.previousOwners = previousOwners;
    }

    // Override displayInfo method
    // This method will display the information of the used vehicle
    @Override   
    public void displayInfo() {
        System.out.println("VIN: " + vin);
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Price: " + price);
        System.out.println("Status: " + status);
        System.out.println("Condition: " + condition);
        System.out.println("Mileage: " + mileage);
        System.out.println("Previous Owners: " + previousOwners);
    }

}
