package com.dealership.ui.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dealership.db.DBConnection;
import com.dealership.models.Vehicle;
import com.dealership.services.Inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class InventoryController {

    @FXML private TextField searchField;
    @FXML private GridPane vehicleGrid;

    private final ObservableList<Vehicle> displayedVehicles = FXCollections.observableArrayList();
    private final Inventory inventory = new Inventory(5);

    @FXML
    public void initialize() {
        updateVehicleGrid();
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().trim().toLowerCase();
        displayedVehicles.setAll(inventory.getAvailableVehicles().stream()
                .filter(v -> v.getMake().toLowerCase().contains(query) ||
                             v.getModel().toLowerCase().contains(query))
                .toList());
        updateVehicleGrid();
    }

    private void updateVehicleGrid() {
        vehicleGrid.getChildren().clear();
        int columnCount = 3;
        int i = 0;
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles");

            while (rs.next()) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(10));
                card.setStyle("-fx-border-color: #ccc; -fx-border-width: 1; -fx-background-color: #f9f9f9;");

                Label vinLabel = new Label("VIN: " + rs.getString("VIN"));
                Label makeLabel = new Label("Make: " + rs.getString("make"));
                Label modelLabel = new Label("Model: " + rs.getString("model"));
                Label yearLabel = new Label("Year: " + rs.getInt("year"));
                Label priceLabel = new Label("Price: $" + rs.getDouble("price"));

                card.getChildren().addAll(vinLabel, makeLabel, modelLabel, yearLabel, priceLabel);

                int col = i % columnCount;
                int row = i / columnCount;
                vehicleGrid.add(card, col, row);
                i++;
            }

            DBConnection.closeConnection(conn);

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
