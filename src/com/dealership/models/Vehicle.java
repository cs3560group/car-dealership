package com.dealership.models;

public abstract class Vehicle {

    // Vehicle class with vin, make, model, year, price, status, and condition attributes
    protected String vin;
    protected String make;
    protected String model;
    protected int year;
    protected double price;
    protected String status;
    protected String condition;

    // Constructor
    public Vehicle(String vin, String make, String model, int year, double price, String status, String condition) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.status = status;
        this.condition = condition;
    }
    
    // Required Methods for class
    public String getVin() {
        return vin;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public double getPrice() {
        return price;
    }
    public String getStatus() {
        return status;
    }
    public String getCondition() {
        return condition;
    }

    public abstract void displayInfo();
}
