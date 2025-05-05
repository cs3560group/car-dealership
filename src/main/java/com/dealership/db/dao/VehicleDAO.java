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

@SuppressWarnings({ "CallToPrintStackTrace", "UseSpecificCatch", "unused" })
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
                        rs.getString("condition"),
                        rs.getString("imagePath"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public static void updateVehicle(Vehicle currentVehicle) {
        String sql = "UPDATE vehicles SET make = ?, model = ?, year = ?, price = ?, status = ?, `condition` = ?, imagePath WHERE VIN = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, currentVehicle.getMake());
            stmt.setString(2, currentVehicle.getModel());
            stmt.setInt(3, currentVehicle.getYear());
            stmt.setDouble(4, currentVehicle.getPrice());
            stmt.setString(5, currentVehicle.getStatus());
            stmt.setString(6, currentVehicle.getCondition());
            stmt.setString(7, currentVehicle.getVin());
            stmt.setString(8, currentVehicle.getImagePath());
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
        String sql = "INSERT INTO vehicles (VIN, make, model, year, price, status, `condition`, imagePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVin());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setDouble(5, vehicle.getPrice());
            stmt.setString(6, vehicle.getStatus());
            stmt.setString(7, vehicle.getCondition());
            stmt.setString(8, vehicle.getImagePath());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}