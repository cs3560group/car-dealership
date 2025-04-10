package com.dealership;

import com.dealership.models.Vehicle; //import needed to store vehicle objects
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int thresholdLimit;
    private ArrayList<Vehicle> vehicles; //list to store vehicle objects

    public Inventory(int thresholdLimit) {
        this.thresholdLimit = thresholdLimit;
        this.vehicles = new ArrayList<>(); //initialize the list of vehicles
    }

    //method to add a vehicle from the inventory
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    //method to remove a vehicle from the inventory by the VIN
    public boolean removeVehicle(String vin){
        return vehicles.removeIf(v -> v.getVin().equalsIgnoreCase(vin));
    }

    //method to get the total number of available vehicles in the inventory
    public List<Vehicle> getAvailableVehicles() {
        return vehicles.stream().filter(v -> !v.isSold()) .toList();
    }

    //method to return the total number of available vehicles in the inventory
    public int getTotalStock() {
        return getAvailableVehicles().size();
    }

    public int getThresholdLimit() {
        return thresholdLimit;
    }
}