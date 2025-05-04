package com.dealership.controllers.admin;

import com.dealership.db.dao.VehicleDAO;
import com.dealership.models.Vehicle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

            {
                editButton.setOnAction(e -> {
                    Vehicle vehicle = getTableView().getItems().get(getIndex());
                    // TODO: Replace this with your own edit logic
                    System.out.println("Editing: " + vehicle.getVin());
                });
                editButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-cursor: hand;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });
    }

    @FXML
    private void handleAddVehicle() {
        System.out.println("Add Vehicle clicked");
        // TODO: Open form or scene to add a new vehicle
    }
}
