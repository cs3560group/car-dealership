package com.dealership.models;

import javafx.beans.property.*;

public abstract class Vehicle {

    // JavaFX properties for binding in TableView
    protected final StringProperty vin;
    protected final StringProperty make;
    protected final StringProperty model;
    protected final IntegerProperty year;
    protected final DoubleProperty price;
    protected final StringProperty status;
    protected final StringProperty condition;

    // Constructor
    public Vehicle(String vin, String make, String model, int year, double price, String status, String condition) {
        this.vin = new SimpleStringProperty(vin);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleIntegerProperty(year);
        this.price = new SimpleDoubleProperty(price);
        this.status = new SimpleStringProperty(status);
        this.condition = new SimpleStringProperty(condition);
    }

    // Getters
    public String getVin() { return vin.get(); }
    public String getMake() { return make.get(); }
    public String getModel() { return model.get(); }
    public int getYear() { return year.get(); }
    public double getPrice() { return price.get(); }
    public String getStatus() { return status.get(); }
    public String getCondition() { return condition.get(); }

    // Property accessors (for JavaFX bindings)
    public StringProperty vinProperty() { return vin; }
    public StringProperty makeProperty() { return make; }
    public StringProperty modelProperty() { return model; }
    public IntegerProperty yearProperty() { return year; }
    public DoubleProperty priceProperty() { return price; }
    public StringProperty statusProperty() { return status; }
    public StringProperty conditionProperty() { return condition; }

    // Setters
    public void setVin(String vin) { this.vin.set(vin); }
    public void setMake(String make) { this.make.set(make); }
    public void setModel(String model) { this.model.set(model); }
    public void setYear(int year) { this.year.set(year); }
    public void setPrice(double price) { this.price.set(price); }
    public void setStatus(String status) { this.status.set(status); }
    public void setCondition(String condition) { this.condition.set(condition); }

    // Business logic
    public boolean isSold() {
        return status.get() != null && status.get().equalsIgnoreCase("sold");
    }

    public void markAsSold() {
        this.status.set("sold");
    }

    public String getDisplayInfo() {
        return "Make: " + getMake() +
               ", Model: " + getModel() +
               ", Year: " + getYear() +
               ", Price: $" + getPrice();
    }

    // Abstract method for subclasses
    public abstract void displayInfo();
}

