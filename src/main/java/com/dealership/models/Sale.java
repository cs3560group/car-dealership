package com.dealership.models;

import java.sql.Timestamp;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sale {
    private IntegerProperty saleID;
    private final ObjectProperty<Timestamp> saleDate;
    private final DoubleProperty salePrice;
    private final StringProperty paymentMethod;
    private final IntegerProperty customerId;
    private final IntegerProperty employeeId;
    private final StringProperty vin;

    // Constructor
    public Sale(int saleID, Timestamp saleDate, double salePrice, String paymentMethod, int customerId, int employeeId,
            String vin) {
        this.saleID = new SimpleIntegerProperty(saleID);
        this.saleDate = new SimpleObjectProperty<>(saleDate);
        this.salePrice = new SimpleDoubleProperty(salePrice);
        this.paymentMethod = new SimpleStringProperty(paymentMethod);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.employeeId = new SimpleIntegerProperty(employeeId);
        this.vin = new SimpleStringProperty(vin);
    }

    public Sale(Timestamp saleDate, double salePrice, String paymentMethod, int customerId, int employeeId,
            String vin) {
        this.saleDate = new SimpleObjectProperty<>(saleDate);
        this.salePrice = new SimpleDoubleProperty(salePrice);
        this.paymentMethod = new SimpleStringProperty(paymentMethod);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.employeeId = new SimpleIntegerProperty(employeeId);
        this.vin = new SimpleStringProperty(vin);
    }

    // Properties
    public IntegerProperty saleIDProperty() {
        return saleID;
    }

    public ObjectProperty<Timestamp> saleDateProperty() {
        return saleDate;
    }

    public DoubleProperty salePriceProperty() {
        return salePrice;
    }

    public StringProperty paymentMethodProperty() {
        return paymentMethod;
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    public IntegerProperty employeeIdProperty() {
        return employeeId;
    }

    public StringProperty vinProperty() {
        return vin;
    }

    // Getters and Setters
    public int getSaleID() {
        return saleID.get();
    }

    public void setSaleID(int saleID) {
        this.saleID.set(saleID);
    }

    public Timestamp getSaleDate() {
        return saleDate.get();
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate.set(saleDate);
    }

    public double getSalePrice() {
        return salePrice.get();
    }

    public void setSalePrice(double salePrice) {
        this.salePrice.set(salePrice);
    }

    public String getPaymentMethod() {
        return paymentMethod.get();
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod.set(paymentMethod);
    }

    public int getCustomerID() {
        return customerId.get();
    }

    public void setCustomerID(int customerID) {
        this.customerId.set(customerID);
    }

    public int getEmployeeID() {
        return employeeId.get();
    }

    public void setEmployeeID(int employeeID) {
        this.employeeId.set(employeeID);
    }

    public String getVin() {
        return vin.get();
    }

    public void setVin(String vin) {
        this.vin.set(vin);
    }
}