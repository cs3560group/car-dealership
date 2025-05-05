package com.dealership.controllers.admin;

import java.io.File;

import com.dealership.auth.Session;
import com.dealership.db.dao.VehicleDAO;
import com.dealership.models.Vehicle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

@SuppressWarnings({ "CallToPrintStackTrace", "UseSpecificCatch", "unused" })
public class EditVehicleController {

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
    private Button deleteButton;
    @FXML
    private Button uploadButton;
    @FXML
    private ImageView carImageView;

    private File selectedImageFile;
    private Vehicle currentVehicle;
    private InventoryController inventoryController;

    @FXML
    public void initialize() {
        if (!Session.hasRole("admin") && !Session.hasRole("manager")) {
            deleteButton.setVisible(false);
        }
    }

    public void setVehicle(Vehicle vehicle) {
        this.currentVehicle = vehicle;
        vinField.setText(vehicle.getVin());
        makeField.setText(vehicle.getMake());
        modelField.setText(vehicle.getModel());
        yearField.setText(String.valueOf(vehicle.getYear()));
        priceField.setText(String.valueOf(vehicle.getPrice()));
        statusField.setText(vehicle.getStatus());
        conditionField.setText(vehicle.getCondition());
        if (vehicle.getImagePath() != null) {
            System.out.println("Image path: " + vehicle.getImagePath());
            carImageView.setImage(new Image("file:images/" + vehicle.getImagePath()));
        } else {
            carImageView.setImage(null);
        }
    }

    public void setInventoryController(InventoryController controller) {
        this.inventoryController = controller;
    }

    @FXML
    private void handleSave() {
        currentVehicle.setMake(makeField.getText());
        currentVehicle.setModel(modelField.getText());
        currentVehicle.setYear(Integer.parseInt(yearField.getText()));
        currentVehicle.setPrice(Double.parseDouble(priceField.getText()));
        currentVehicle.setStatus(statusField.getText());
        currentVehicle.setCondition(conditionField.getText());
        if (selectedImageFile != null) {
            currentVehicle.setImagePath(selectedImageFile.getName());
        } else {
            currentVehicle.setImagePath(null);
        }
        try {
            VehicleDAO.updateVehicle(currentVehicle);
            inventoryController.refreshVehicleTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vehicle updated successfully.");
            alert.showAndWait();
            ((Stage) vinField.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        try {
            VehicleDAO.deleteVehicle(currentVehicle.getVin());
            inventoryController.refreshVehicleTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vehicle deleted successfully.");
            alert.showAndWait();
            ((Stage) vinField.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Car Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File file = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if (file != null) {
            try {
                // Define a runtime-accessible folder (e.g., ./images/)
                File destinationDir = new File("images");
                if (!destinationDir.exists()) {
                    destinationDir.mkdirs(); // create directory if it doesn't exist
                }

                // Copy the selected image file into the images folder
                File destFile = new File(destinationDir, file.getName());
                java.nio.file.Files.copy(file.toPath(), destFile.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                // Save only the filename
                selectedImageFile = destFile;
                carImageView.setImage(new Image(destFile.toURI().toString()));

            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Failed to upload image: " + e.getMessage());

            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
}
