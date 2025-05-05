package com.dealership.controllers.admin;

import java.io.IOException;

import com.dealership.db.dao.VehicleDAO;
import com.dealership.models.Vehicle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InventoryController {
    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> vinCol;
    @FXML
    private TableColumn<Vehicle, String> makeCol;
    @FXML
    private TableColumn<Vehicle, String> modelCol;
    @FXML
    private TableColumn<Vehicle, Integer> yearCol;
    @FXML
    private TableColumn<Vehicle, Double> priceCol;
    @FXML
    private TableColumn<Vehicle, String> statusCol;
    @FXML
    private TableColumn<Vehicle, String> conditionCol;
    @FXML
    private TableColumn<Vehicle, Void> actionCol;

    private final ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        vinCol.setCellValueFactory(data -> data.getValue().vinProperty());
        makeCol.setCellValueFactory(data -> data.getValue().makeProperty());
        modelCol.setCellValueFactory(data -> data.getValue().modelProperty());
        yearCol.setCellValueFactory(data -> data.getValue().yearProperty().asObject());
        priceCol.setCellValueFactory(data -> data.getValue().priceProperty().asObject());
        statusCol.setCellValueFactory(data -> data.getValue().statusProperty());
        conditionCol.setCellValueFactory(data -> data.getValue().conditionProperty());
        addEditButtonToTable();
        try {
            vehicleList.setAll(VehicleDAO.getAllVehicles());
            vehicleTable.setItems(vehicleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addEditButtonToTable() {
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-cursor: hand;");
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-cursor: hand;");

                editButton.setOnAction(e -> {
                    Vehicle vehicle = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/com/dealership/views/admin_view/edit_vehicle_view.fxml"));
                        Parent root = loader.load();
                        EditVehicleController controller = loader.getController();
                        controller.setVehicle(vehicle);
                        controller.setInventoryController(InventoryController.this);

                        Stage stage = new Stage();
                        stage.setTitle("Edit Vehicle");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                deleteButton.setOnAction(e -> {
                    Vehicle vehicle = getTableView().getItems().get(getIndex());
                    try {
                        VehicleDAO.deleteVehicle(vehicle.getVin());
                        refreshVehicleTable();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(10, editButton, deleteButton);
                    setGraphic(buttons);
                }
            }
        });
    }

    @FXML
    private void handleAddVehicle() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/dealership/views/admin_view/add_vehicle_view.fxml"));
            Parent root = loader.load();
            AddVehicleController controller = loader.getController();
            controller.setInventoryController(this);

            Stage stage = new Stage();
            stage.setTitle("Add Vehicle");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void refreshVehicleTable() {
        try {
            vehicleList.setAll(VehicleDAO.getAllVehicles());
            vehicleTable.setItems(vehicleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
