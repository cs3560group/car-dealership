package com.dealership.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehicle {

    // JavaFX properties for binding in TableView
    protected final StringProperty vin;
    protected final StringProperty make;
    protected final StringProperty model;
    protected final IntegerProperty year;
    protected final DoubleProperty price;
    protected final StringProperty status;
    protected final StringProperty condition;
    protected StringProperty imagePath;
    protected IntegerProperty inventoryId;

    // Constructor
    public Vehicle(String vin, String make, String model, int year, double price, String status, String condition,
            String imagePath) {
        this.vin = new SimpleStringProperty(vin);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleIntegerProperty(year);
        this.price = new SimpleDoubleProperty(price);
        this.status = new SimpleStringProperty(status);
        this.condition = new SimpleStringProperty(condition);
        this.imagePath = new SimpleStringProperty(imagePath);
    }

    // Removed duplicate constructor to resolve the compile error

    public Vehicle(String vin, String make, String model, int year, double price, String status, String condition,
            String imagePath, int inventoryId) {
        this.vin = new SimpleStringProperty(vin);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleIntegerProperty(year);
        this.price = new SimpleDoubleProperty(price);
        this.status = new SimpleStringProperty(status);
        this.condition = new SimpleStringProperty(condition);
        this.inventoryId = new SimpleIntegerProperty(inventoryId);
        this.imagePath = new SimpleStringProperty(imagePath);
    }

    // Getters
    public String getVin() {
        return vin.get();
    }

    public String getMake() {
        return make.get();
    }

    public String getModel() {
        return model.get();
    }

    public int getYear() {
        return year.get();
    }

    public double getPrice() {
        return price.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getCondition() {
        return condition.get();
    }

    public String getImagePath() {
        return imagePath.get();
    }

    public int getInventoryId() {
        return inventoryId.get();
    }

    // Property accessors (for JavaFX bindings)
    public StringProperty vinProperty() {
        return vin;
    }

    public StringProperty makeProperty() {
        return make;
    }

    public StringProperty modelProperty() {
        return model;
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty conditionProperty() {
        return condition;
    }

    public StringProperty imagePathProperty() {
        return imagePath;
    }

    public IntegerProperty inventoryIdProperty() {
        return inventoryId;
    }

    // Setters
    public void setVin(String vin) {
        this.vin.set(vin);
    }

    public void setMake(String make) {
        this.make.set(make);
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }

    public void setImagePath(String imagePath) {
        this.imagePath.set(imagePath);
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId.set(inventoryId);
    }

    // Business logic
    public boolean isSold() {
        return status.get() != null && status.get().equalsIgnoreCase("sold");
    }

    public void markAsSold() {
        this.status.set("sold");
    }

    public void markAsAvailable() {
        this.status.set("available");
    }

}
