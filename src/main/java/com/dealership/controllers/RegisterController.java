package com.dealership.controllers;

import java.sql.SQLException;

import com.dealership.db.dao.UserDAO;
import com.dealership.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    TextField nameField;
    @FXML
    TextField emailField;
    @FXML
    PasswordField passwordField;
    @FXML
    private ComboBox<String> roleCombo;

    @FXML
    public void initialize() {
        roleCombo.getItems().addAll("Employee", "Manager", "Admin");
        roleCombo.getSelectionModel().selectFirst(); // Optional default
    }

    /**
     * Handles the registration button click event.
     */
    public void handleRegister() {
        try {
            UserDAO.registerUser(
                    new User(nameField.getText(), passwordField.getText(), roleCombo.getValue(), emailField.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
            // Show error message to the user
            System.out.println("An error occurred while registering the user.");
        }
    }

    public void handleReturn() {
        // Go back to the previous scene
        SceneManager.goBack();
    }
}
