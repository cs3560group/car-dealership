package com.dealership.models;

public class Employee {
    private String name; 
    private String role;
    private String ID;

    public Employee(String name, String role, String ID) {
        this.name = name;
        this.role = role;
        this.ID = ID;
    }
    
    public String getName(){
        return name;
    }
    public String getRole(){
        return role;
    }
    public String getID(){
        return ID;
    }
}
