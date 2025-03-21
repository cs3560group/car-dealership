package com.dealership.services;

public class Management {
    private String managerID;
    private String name;

    public Management(String managerID, String name){
        this.managerID = managerID;
        this.name = name;
    }
    public String getManagerID() {
        return managerID;
    }
    public String getName(){
        return name;
    }
    
}
