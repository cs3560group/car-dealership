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

    public void registerUser() throws SQLException {
        String name = nameField.getText();
        String emailText = emailField.getText();
        String passwordText = passwordField.getText();
        String role = roleCombo.getValue().toLowerCase();

        // Validate input fields
        if (name.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || role.isEmpty()) {
            // Show error message to the user
            System.out.println("Please fill in all fields.");
            return;
        }

        User newUser = new User(name, passwordText, role, emailText);
        boolean isRegistered = UserDAO.addUser(newUser); // Call UserDAO to register the user
        if (isRegistered) {
            // Show success message to the user
            System.out.println("User registered successfully.");
        } else {
            // Show error message to the user
            System.out.println("Registration failed. Please try again.");
        }
    }

    public void handleRegister() {
        try {
            registerUser();
        } catch (SQLException e) {
            e.printStackTrace();
            // Show error message to the user
            System.out.println("An error occurred while registering the user.");
        }
    }

    public void handleReturn() {

    }
}
