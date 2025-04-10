package com.dealership.models;

public class Supplier {

    // Supplier class with name, phone number, email, and ID attributes
    private String supplierName;
    private String supplierPhoneNumber;
    private String supplierEmail;
    private String supplierID;

    // Constructor
    public Supplier(String supplierName, String supplierPhoneNumber, String supplierEmail, String supplierID) {
        this.supplierName = supplierName;
        this.supplierPhoneNumber = supplierPhoneNumber;
        this.supplierEmail = supplierEmail;
        this.supplierID = supplierID;
    }

    // Required Methods for class
    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setSupplierPhoneNumber(String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void printSupplierInfo() {
        System.out.println("Supplier Name: " + supplierName);
        System.out.println("Supplier Phone Number: " + supplierPhoneNumber);
        System.out.println("Supplier Email: " + supplierEmail);
        System.out.println("Supplier ID: " + supplierID);
    }
}
