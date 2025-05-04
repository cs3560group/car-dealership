package com.dealership.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dealership.models.Vehicle;
import com.dealership.models.NewVehicle;
import com.dealership.models.UsedVehicle;

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
    
    /**
     * Creates a Vehicle object from a database result set.
     * This method handles creating the appropriate Vehicle subclass based on condition.
     * 
     * @param rs The ResultSet containing vehicle data
     * @return A Vehicle object populated with data from the ResultSet
     * @throws SQLException If there's an error accessing the ResultSet
     */
    public Vehicle createVehicleFromResultSet(ResultSet rs) throws SQLException {
        // Get common fields from ResultSet
        String vin = rs.getString("VIN");
        String make = rs.getString("make");
        String model = rs.getString("model");
        int year = rs.getInt("year");
        double price = rs.getDouble("price");
        String status = rs.getString("status");
        String condition = rs.getString("condition");
        
        // Create the appropriate vehicle type based on condition
        Vehicle vehicle;
        
        if ("New".equalsIgnoreCase(condition)) {
            // For new vehicles, use default warranty of 3 years
            // In a real app, you might join with newvehicles table to get actual warranty
            int warranty = 3; // Default warranty in years
            vehicle = new NewVehicle(vin, make, model, year, price, status, condition, warranty);
        } else {
            // For used vehicles, use default mileage and owners if not available
            // In a real app, you might join with usedvehicles table
            int mileage = 50000; // Default mileage
            int previousOwners = 1; // Default number of previous owners
            
            // Try to get actual mileage and previous owners if they exist in the result set
            try {
                mileage = rs.getInt("mileage");
                previousOwners = rs.getInt("previousOwners");
            } catch (SQLException e) {
                // Fields not available, use defaults
                System.out.println("Using default values for mileage and previous owners");
            }
            
            vehicle = new UsedVehicle(vin, make, model, year, price, status, condition, 
                                     mileage, previousOwners);
        }
        
        return vehicle;
    }
}