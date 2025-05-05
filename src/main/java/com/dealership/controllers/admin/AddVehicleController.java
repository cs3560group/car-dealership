package com.dealership.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.dealership.db.dao.VehicleDAO;
import com.dealership.models.Vehicle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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
    private Button uploadButton;
    @FXML
    private ImageView carImageView;
    @FXML
    private TextField conditionField;

    private File selectedImageFile;
    private String vehicleImagePath;
    private InventoryController inventoryController;

    @FXML
    @SuppressWarnings({ "CallToPrintStackTrace", "UseSpecificCatch", "unused" })
    private void handleAddVehicle() {
        String imagePath = selectedImageFile != null ? selectedImageFile.getAbsolutePath() : null;
        try {
            Vehicle vehicle = new Vehicle(
                    vinField.getText(),
                    makeField.getText(),
                    modelField.getText(),
                    Integer.parseInt(yearField.getText()),
                    Double.parseDouble(priceField.getText()),
                    statusField.getText(),
                    conditionField.getText(),
                    imagePath);

            VehicleDAO.addVehicle(vehicle);
            showAlert(Alert.AlertType.INFORMATION, "Vehicle added successfully!");
            if (inventoryController != null) {
                inventoryController.refreshVehicleTable();
            }
            closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error adding vehicle.");
        }
    }

    @FXML
    @SuppressWarnings("unused")
    private void handleUploadImage() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Car Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if (selectedFile != null) {
            try {
                File destDir = new File("src/main/resources/images/");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }

                String fileName = selectedFile.getName();
                File destFile = new File(destDir, fileName);

                // Copy file into resources
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Store relative path in DB
                String relativePath = "src/main/resources/images/" + fileName;
                selectedImageFile = destFile; // Still keep reference if needed
                vehicleImagePath = relativePath; // Save this to DB

                // Show preview
                carImageView.setImage(new Image(destFile.toURI().toString()));

                System.out.println("Stored relative path: " + relativePath);

            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Failed to copy image.");
            }
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
