package com.dealership.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dealership.db.DBConnection;
import com.dealership.models.Vehicle;

public class VehicleDAO {
    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles");
            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getString("VIN"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("status"),
                        rs.getString("condition"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public static void updateVehicle(Vehicle currentVehicle) {
        String sql = "UPDATE vehicles SET make = ?, model = ?, year = ?, price = ?, status = ?, `condition` = ? WHERE VIN = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, currentVehicle.getMake());
            stmt.setString(2, currentVehicle.getModel());
            stmt.setInt(3, currentVehicle.getYear());
            stmt.setDouble(4, currentVehicle.getPrice());
            stmt.setString(5, currentVehicle.getStatus());
            stmt.setString(6, currentVehicle.getCondition());
            stmt.setString(7, currentVehicle.getVin());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVehicle(String vin) {
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            String sql = String.format("DELETE FROM vehicles WHERE VIN='%s'", vin);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addVehicle(Vehicle vehicle) {
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            String sql = String.format(
                    "INSERT INTO vehicles (VIN, make, model, year, price, status, `condition`) VALUES ('%s', '%s', '%s', %d, %.2f, '%s', '%s')",
                    vehicle.getVin(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(),
                    vehicle.getPrice(), vehicle.getStatus(), vehicle.getCondition());
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}