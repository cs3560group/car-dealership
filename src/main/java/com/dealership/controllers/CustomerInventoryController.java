package com.dealership.controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.kordamp.ikonli.javafx.FontIcon;

import com.dealership.db.DBConnection;
import com.dealership.models.Vehicle;
import com.dealership.services.Inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for the inventory view.
 * Handles displaying vehicles and search functionality.
 */
public class CustomerInventoryController {

    // UI elements injected by JavaFX
    @FXML
    private TextField searchField; // Text field where user enters search terms
    @FXML
    private GridPane vehicleGrid; // Grid layout to display vehicle cards
    @FXML
    private Label resultsLabel;
    @FXML
    private VBox emptyState;
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
        searchField.setOnAction(event -> handleSearch());

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
     * 
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

            // Update the UI with search results
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

        // Update results count label
        int count = displayedVehicles.size();
        resultsLabel.setText("Showing " + count + (count == 1 ? " vehicle" : " vehicles"));

        // Show empty state if no results found
        if (count == 0) {
            emptyState.setVisible(true);
            vehicleGrid.setVisible(false);
            return;
        } else {
            emptyState.setVisible(false);
            vehicleGrid.setVisible(true);
        }

        // Define how many columns to use in the grid
        int columnCount = 3;

        // Loop through each vehicle in our filtered list
        for (int i = 0; i < displayedVehicles.size(); i++) {
            Vehicle vehicle = displayedVehicles.get(i);

            // Create a card for each vehicle
            VBox card = createVehicleCard(vehicle);

            // Calculate grid position for this card
            int col = i % columnCount;
            int row = i / columnCount;

            // Add the card to the grid at the calculated position
            vehicleGrid.add(card, col, row);
        }
    }

    /**
     * Creates a styled vehicle card with all necessary information.
     * 
     * @param vehicle The vehicle object to display
     * @return A VBox containing the styled vehicle card
     */
    private VBox createVehicleCard(Vehicle vehicle) {
        // Main card container
        VBox card = new VBox();
        card.getStyleClass().add("vehicle-card");

        // Vehicle image placeholder
        StackPane imagePlaceholder = new StackPane();
        imagePlaceholder.getStyleClass().add("vehicle-image");

        // Label imageLabel = new Label(vehicle.getMake() + " " + vehicle.getModel() + "
        // Image");
        // imageLabel.getStyleClass().add("vehicle-image-placeholder");
        // imagePlaceholder.getChildren().add(imageLabel);
        ImageView vehicleImage = new ImageView();
        vehicleImage.setFitWidth(200);
        vehicleImage.setFitHeight(120);
        vehicleImage.setPreserveRatio(true);
        vehicleImage.setSmooth(true);
        try {
            if (vehicle.getImagePath() != null && !vehicle.getImagePath().isBlank()) {
                File imageFile = new File("images/" + vehicle.getImagePath());
                if (imageFile.exists()) {
                    vehicleImage.setImage(new Image(imageFile.toURI().toString()));
                    imagePlaceholder.getChildren().add(vehicleImage);
                } else {
                    throw new Exception("File not found");
                }
            } else {
                throw new Exception("Empty image path");
            }
        } catch (Exception e) {
            Label fallback = new Label("Image not found");
            fallback.getStyleClass().add("vehicle-image-placeholder");
            imagePlaceholder.getChildren().add(fallback);
        }
        // Vehicle info container
        VBox infoContainer = new VBox();
        infoContainer.getStyleClass().add("vehicle-info");

        // Header with title and status
        HBox header = new HBox();
        header.getStyleClass().add("vehicle-header");

        Label titleLabel = new Label(vehicle.getMake() + " " + vehicle.getModel());
        titleLabel.getStyleClass().add("vehicle-title");

        Label statusLabel = new Label(vehicle.getStatus());
        statusLabel.getStyleClass().addAll("status-badge", "status-" + vehicle.getStatus().toLowerCase());

        HBox.setHgrow(titleLabel, Priority.ALWAYS);
        header.getChildren().addAll(titleLabel, statusLabel);

        // Vehicle details
        VBox details = new VBox(8);

        // VIN detail
        HBox vinDetail = createDetailRow("fas-tag", "VIN: " + vehicle.getVin());

        // Year detail
        HBox yearDetail = createDetailRow("fas-calendar", "Year: " + vehicle.getYear());

        // Price detail
        HBox priceDetail = createDetailRow("fas-dollar-sign", "Price: $" + vehicle.getPrice());
        priceDetail.getStyleClass().add("vehicle-price");

        // Condition detail
        HBox conditionDetail = createDetailRow(
                vehicle.getCondition().equals("New") ? "fas-star" : "fas-check",
                "Condition: " + vehicle.getCondition());
        conditionDetail.getStyleClass().add("condition-" + vehicle.getCondition().toLowerCase());

        details.getChildren().addAll(vinDetail, yearDetail, priceDetail, conditionDetail);

        // View details button
        Button detailsButton = new Button("View Details");
        detailsButton.getStyleClass().add("details-button");

        // Add all elements to the info container
        infoContainer.getChildren().addAll(header, details, detailsButton);

        // Add elements to the main card
        card.getChildren().addAll(imagePlaceholder, infoContainer);

        return card;
    }

    /**
     * Creates a detail row with an icon and text.
     * 
     * @param iconLiteral The icon literal (FontIcon)
     * @param text        The text to display
     * @return An HBox containing the icon and text
     */
    private HBox createDetailRow(String iconLiteral, String text) {
        HBox row = new HBox();
        row.getStyleClass().add("vehicle-detail");

        FontIcon icon = new FontIcon(iconLiteral);
        icon.getStyleClass().add("detail-icon");

        Label label = new Label(text);

        row.getChildren().addAll(icon, label);
        return row;
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.goBack();
    }
}
