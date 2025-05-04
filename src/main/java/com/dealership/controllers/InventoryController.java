package com.dealership.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.dealership.db.DBConnection;
import com.dealership.models.Vehicle;
import com.dealership.services.Inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for the inventory view.
 * Handles displaying vehicles and search functionality.
 */
public class InventoryController {

    // UI elements injected by JavaFX
    @FXML
    private TextField searchField; // Text field where user enters search terms
    @FXML
    private GridPane vehicleGrid; // Grid layout to display vehicle cards

    // Keep the ObservableList for tracking displayed vehicles
    private final ObservableList<Vehicle> displayedVehicles = FXCollections.observableArrayList();
    private final Inventory inventory = new Inventory(5);

    /**
     * Initializes the controller.
     * This method is automatically called by JavaFX after the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        // When the view first loads, populate it with all vehicles from the database
        loadAllVehicles();
    }

    /**
     * Handles user input in the search field. 
     * This method is called when the user clicks the search button or 
     * presses Enter in the search field (configured in FXML).
     */
    @FXML
    private void handleSearch() {
        // Get text from search field, remove whitespace, and convert to lowercase
        String query = searchField.getText().trim().toLowerCase();
        
        if (query.isEmpty()) {
            // If search field is empty, load all vehicles
            loadAllVehicles();
        } else {
            // Otherwise, perform a filtered search using query
            searchVehicles(query);
        }
    }

    /**
     * Loads all vehicles from the database without any filtering.
     * Called when the view is initialized or when the search field is empty.
     */
    private void loadAllVehicles() {
        try (Connection conn = DBConnection.getConnection()) {
            // Create a statement for executing SQL
            Statement stmt = conn.createStatement();

            // Execute a query to get all vehicles
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles");

            // Clear previous results from our display list
            displayedVehicles.clear();

            // Process each row from the result set
            while (rs.next()) {
                // Get the vehicle data from the database and add to our list
                displayedVehicles.add(inventory.createVehicleFromResultSet(rs));
            }
            
            // Update the UI to show the vehicles
            updateVehicleGrid();
        } catch (SQLException e) {
            // Log any database errors
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Searches for vehicles that match the given query string.
     * Filters by vehicle make or model containing the search term.
     * @param query The search term entered by the user
     */
    private void searchVehicles(String query) {
        try (Connection conn = DBConnection.getConnection()) {
            // Create a SQL query with placeholders for the search term
            // LOWER() converts database values to lowercase for case-insensitive search
            // LIKE with % wildcards matches substrings (contains)
            String sql = "SELECT * FROM vehicles WHERE LOWER(make) LIKE ? OR LOWER(model) LIKE ?";

            // Create a prepared statement (safer than raw SQL, prevents SQL injection)
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Create the pattern with wildcards (% means "any characters")
            String likePattern = "%" + query + "%";

            // Set the parameters for the prepared statement
            // Both parameters get the same search term to find matches in make OR model
            pstmt.setString(1, likePattern); // First ? in the SQL query (make)
            pstmt.setString(2, likePattern); // Second ? in the SQL query (model)

            // Execute the query and get results
            ResultSet rs = pstmt.executeQuery();

            // Clear previous results
            displayedVehicles.clear();

            // Process each matching vehicle
            while (rs.next()) {
                // Get the vehicle data from the database and add to our list
                displayedVehicles.add(inventory.createVehicleFromResultSet(rs));
            }

            // Update the UI with search results;
            updateVehicleGrid();
        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Updates the UI grid to display the currently filtered vehicles. 
     * Creates a card for each vehicle and arranges them into a grid layout.
     */
    private void updateVehicleGrid() {
        // Clear existing content from the grid
        vehicleGrid.getChildren().clear();

        // Define how many columns to use in the grid
        int columnCount = 3;
        
        // Loop through each vehicle in our filtered list
        for (int i = 0; i < displayedVehicles.size(); i++) {
            Vehicle vehicle = displayedVehicles.get(i);

            // Create a card (VBox) for each vehicle
            VBox card = new VBox(5); // 5 is the spacing between elements
            card.setPadding(new Insets(10)); // 10px padding inside the card
            card.setStyle("-fx-border-color: #ccc; -fx-border-width: 1; -fx-background-color: #f9f9f9;");

            // Create labels for each vehicle property - use Vehicle's getters
            Label vinLabel = new Label("VIN: " + vehicle.getVin());  
            Label makeLabel = new Label("Make: " + vehicle.getMake());
            Label modelLabel = new Label("Model: " + vehicle.getModel());
            Label yearLabel = new Label("Year: " + vehicle.getYear());
            Label priceLabel = new Label("Price: $" + vehicle.getPrice());
            Label statusLabel = new Label("Status: " + vehicle.getStatus());
            Label conditionLabel = new Label("Condition: " + vehicle.getCondition());

            // Add all labels to the card
            card.getChildren().addAll(vinLabel, makeLabel, modelLabel, yearLabel, 
                                     priceLabel, statusLabel, conditionLabel);

            // Calculate grid position for this card
            // The modulo (%) gives the column index (0, 1, or 2)
            int col = i % columnCount;
            // Integer division gives the row index
            int row = i / columnCount;
            
            // Add the card to the grid at the calculated position
            vehicleGrid.add(card, col, row);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.goBack();
    }
}