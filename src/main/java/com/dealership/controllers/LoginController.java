package com.dealership.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.dealership.db.DBConnection;
import com.dealership.db.dao.UserDAO;
import com.dealership.models.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField idOrEmailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    @SuppressWarnings("unused")
    /**
     * Handles the login button click event.
     * Validates the user credentials and switches to the inventory view if valid.
     * 
     * @throws IOException
     * @throws SQLException
     */
    private void handleLogin() throws IOException, SQLException {
        String idOrEmail = idOrEmailField.getText();
        String password = passwordField.getText();
        if (idOrEmail.isEmpty() || password.isEmpty()) {
            System.out.println("All fields must be entered!");
        }

        try (Connection conn = DBConnection.getConnection()) {
            User user = UserDAO.validateUser(idOrEmail, password);
            if (user != null) {
                SceneManager.setUser(user);
                Parent inventoryView = FXMLLoader
                        .load(getClass().getResource("/com/dealership/views/InventoryView.fxml"));
                Scene root = new Scene(inventoryView);
                SceneManager.switchScene(root);
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }
    }

    /**
     * Handles the return button click event.
     * Switches to the previous scene.
     * 
     * @throws IOException
     */
    @FXML
    @SuppressWarnings("unused")
    private void goBack() {
        SceneManager.goBack();
    }
}
