package com.dealership;

import com.dealership.models.UsedVehicle;
import com.dealership.models.Vehicle;
import com.dealership.Inventory;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private final ObservableList<Vehicle> displayedVehicles = FXCollections.observableArrayList();
    private final Inventory inventory = new Inventory(5);
    private final GridPane vehicleGrid = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        // Initial Vehicles
        inventory.addVehicle(new UsedVehicle("1HGCM82633A123456", "Honda", "Accord", 2020, 20000, "available", "good", 30000, 1));
        inventory.addVehicle(new UsedVehicle("1HGCM82633A123457", "Toyota", "Camry", 2019, 18000, "available", "great", 25000, 2));
        inventory.addVehicle(new UsedVehicle("1HGCM82633A123458", "Ford", "Fusion", 2018, 16000, "available", "excellent", 27000, 3));
        inventory.addVehicle(new UsedVehicle("1HGCM82633A123457", "Toyota", "Camry", 2016, 17350, "available", "bad", 100000, 5));
        inventory.addVehicle(new UsedVehicle("1HGCM82633A123458", "Lamborghini", "Aventador", 2014, 200000, "available", "great", 34000, 2));

        displayedVehicles.addAll(inventory.getAvailableVehicles());

        // Search UI
        TextField searchField = new TextField();
        searchField.setPromptText("Search by make or model");
        Button searchButton = new Button("Search");

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setPadding(new Insets(10));

        // Setup grid layout
        vehicleGrid.setPadding(new Insets(10));
        vehicleGrid.setHgap(20);
        vehicleGrid.setVgap(20);
        updateVehicleGrid();

        // Search logic
        searchButton.setOnAction(e -> {
            String query = searchField.getText().trim().toLowerCase();
            displayedVehicles.setAll(inventory.getAvailableVehicles().stream()
                .filter(v -> v.getMake().toLowerCase().contains(query) ||
                             v.getModel().toLowerCase().contains(query))
                .toList());
            updateVehicleGrid();
        });

        VBox root = new VBox(10, searchBox, vehicleGrid);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Car Dealership Inventory");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateVehicleGrid() {
        vehicleGrid.getChildren().clear();
        int columnCount = 3;
        for (int i = 0; i < displayedVehicles.size(); i++) {
            Vehicle v = displayedVehicles.get(i);

            VBox card = new VBox(5);
            card.setPadding(new Insets(10));
            card.setStyle("-fx-border-color: #ccc; -fx-border-width: 1; -fx-background-color: #f9f9f9;");

            Label vinLabel = new Label("VIN: " + v.getVin());
            Label makeLabel = new Label("Make: " + v.getMake());
            Label modelLabel = new Label("Model: " + v.getModel());
            Label yearLabel = new Label("Year: " + v.getYear());
            Label priceLabel = new Label("Price: $" + v.getPrice());

            card.getChildren().addAll(vinLabel, makeLabel, modelLabel, yearLabel, priceLabel);

            int col = i % columnCount;
            int row = i / columnCount;
            vehicleGrid.add(card, col, row);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


