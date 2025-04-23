package com.dealership.models;

public class Customer {

    // Customer class with name, phone number, email, and ID attributes
    private String name;
    private String phoneNumber;
    private String email;
    private String ID;

    // Constructor
    public Customer(String name, String phoneNumber, String email, String ID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ID = ID;
    }

    //Required Methods for class
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void printCustomerInfo() {
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("ID: " + ID);
    }

    public String getCustomerInfo() {
        return "Name: " + name + "\n"
             + "Phone Number: " + phoneNumber + "\n"
             + "Email: " + email + "\n"
             + "ID: " + ID;
    }
}
