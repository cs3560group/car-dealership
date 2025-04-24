package com.dealership.models;

public class Employee {

    // Employee class with name, role, and ID attributes
    private final String name;
    private final String role;
    private final String ID;

    // Constructor
    public Employee(String name, String role, String ID) {
        this.name = name;
        this.role = role;
        this.ID = ID;
    }

    // Required Methods for class
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getID() {
        return ID;
    }
}
