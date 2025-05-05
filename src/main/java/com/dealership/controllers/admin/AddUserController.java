package com.dealership.controllers.admin;

import com.dealership.db.dao.UserDAO;
import com.dealership.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch", "unused"})
public class AddUserController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button submitButton;

    private UserController userController;

    @FXML
    private void initialize() {
        roleComboBox.getItems().addAll("Admin", "Employee", "Manager");
    }

    @FXML
    private void handleSubmit() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue().toLowerCase().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
            showAlert(Alert.AlertType.ERROR, "Please fill in all fields.");
            return;
        }

        User newUser = new User(name, email, password, role);
        try {
            UserDAO.addUser(newUser);
            showAlert(Alert.AlertType.INFORMATION, "User added successfully.");
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
            if (userController != null) {
                userController.refreshUserTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Failed to add user.");
        }
    }

    
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    
}
