package com.dealership.models;

public class NewVehicle extends Vehicle {

    // NewVehicle class with warranty attribute
    private final int warranty;

    // Constructor
    public NewVehicle(String vin, String make, String model, int year, double price, String status, String condition,
            int warranty) {
        super(vin, make, model, year, price, status, condition);
        this.warranty = warranty;
    }

    //Override displayInfo method
    //This method will display the information of the new vehicle
    public void displayInfo() {
        System.out.println("VIN: " + vin);
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Price: " + price);
        System.out.println("Status: " + status);
        System.out.println("Condition: " + condition);
        System.out.println("Warranty: " + warranty);
    }
}