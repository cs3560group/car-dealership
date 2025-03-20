package com.dealership.models;

public class UsedVehicle extends Vehicle {
    private int mileage;
    private int previousOwners;

    public UsedVehicle(String vin, String make, String model, int year, double price, String status, String condition,
            int mileage, int previousOwners) {
        super(vin, make, model, year, price, status, condition);
        this.mileage = mileage;
        this.previousOwners = previousOwners;
    }

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
    }

}
