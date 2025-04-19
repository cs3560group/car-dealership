package com.dealership.ui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dealership.db.DBConnection;
import com.dealership.models.Vehicle;
import com.dealership.services.Inventory;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

        private final ObservableList<Vehicle> displayedVehicles = FXCollections.observableArrayList();
        private final Inventory inventory = new Inventory(5);
        private final GridPane vehicleGrid = new GridPane();

        @Override
        public void start(Stage primaryStage) {
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
                int i = 0;
                try (Connection conn = DBConnection.getConnection()) {
                        System.out.println("✅ Connected to MySQL!");

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
                                System.out.println("Name: " + rs.getString("VIN") + ", Make: " + rs.getString("make")
                                                + ", model: " + rs.getString("model") + ", year: "
                                                + rs.getInt("year") + ", price: " + rs.getDouble("price"));
                                i++;
                        }
                        DBConnection.closeConnection(conn);

                } catch (SQLException e) {
                        System.out.println("Connection failed: " + e.getMessage());
                }

        }

        public static void main(String[] args) {
                launch(args);
                try (Connection conn = DBConnection.getConnection()) {
                        System.out.println("✅ Connected to MySQL!");

                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles");

                        while (rs.next()) {
                                System.out.println("Name: " + rs.getString("VIN") + ", Make: " + rs.getString("make")
                                                + ", model: " + rs.getString("model") + ", year: "
                                                + rs.getInt("year") + ", price: " + rs.getDouble("price"));
                        }
                        DBConnection.closeConnection(conn);

                } catch (SQLException e) {
                        System.out.println("Connection failed: " + e.getMessage());
                }
        }
}
