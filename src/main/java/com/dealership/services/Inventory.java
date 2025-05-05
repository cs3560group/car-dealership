package com.dealership.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dealership.models.Vehicle;

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
        return vehicles.stream().filter(v -> !v.isSold()).toList();
    }

    //method to return the total number of available vehicles in the inventory
    public int getTotalStock() {
        return getAvailableVehicles().size();
    }

    public int getThresholdLimit() {
        return thresholdLimit;
    }
    
    /**
     * Creates a Vehicle object from a database result set.
     * 
     * @param rs The ResultSet containing vehicle data
     * @return A Vehicle object populated with data from the ResultSet
     * @throws SQLException If there's an error accessing the ResultSet
     */
    public Vehicle createVehicleFromResultSet(ResultSet rs) throws SQLException {
        // Get fields from ResultSet
        String vin = rs.getString("VIN");
        String make = rs.getString("make");
        String model = rs.getString("model");
        int year = rs.getInt("year");
        double price = rs.getDouble("price");
        String status = rs.getString("status");
        String condition = rs.getString("condition");
        
        // Create a new Vehicle instance with the extracted data
        return new Vehicle(vin, make, model, year, price, status, condition);
    }
}
