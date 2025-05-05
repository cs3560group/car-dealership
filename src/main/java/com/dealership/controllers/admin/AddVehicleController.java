package com.dealership.controllers.admin;

import com.dealership.db.dao.VehicleDAO;
import com.dealership.models.Vehicle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVehicleController {

    @FXML
    private TextField vinField;
    @FXML
    private TextField makeField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField statusField;
    @FXML
    private TextField conditionField;
    @FXML
    private TextField inventoryIdField;
    private InventoryController inventoryController;

    @FXML
private void handleAddVehicle() {
    try {
        Vehicle vehicle = new Vehicle(
                vinField.getText(),
                makeField.getText(),
                modelField.getText(),
                Integer.parseInt(yearField.getText()),
                Double.parseDouble(priceField.getText()),
                statusField.getText(),
                conditionField.getText(),
                Integer.parseInt(inventoryIdField.getText())); // Add inventoryId here

        VehicleDAO.addVehicle(vehicle);
        showAlert(Alert.AlertType.INFORMATION, "Vehicle added successfully!");
        if (inventoryController != null) {
            inventoryController.refreshVehicleTable();
        }
        closeWindow();
    } catch (Exception e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Error adding vehicle: " + e.getMessage());
    }
}

    private void closeWindow() {
        Stage stage = (Stage) vinField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg, ButtonType.OK).showAndWait();
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }
}
