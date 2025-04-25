package com.dealership.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.dealership.auth.Session;
import com.dealership.db.DBConnection;
import com.dealership.db.dao.UserDAO;
import com.dealership.models.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Error");
            alert.setContentText("Please enter both ID/Email and Password.");
            alert.showAndWait();
            System.out.println("All fields must be entered!");
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                User user = UserDAO.validateUser(idOrEmail, password);
                Scene root;
                if (user != null) {
                    Session.setCurrentUser(user);
                    if (user.getRole().equals("admin")) {
                        Parent userManagementView = FXMLLoader
                                .load(getClass().getResource("/com/dealership/views/UserManagementView.fxml"));
                        root = new Scene(userManagementView);
                        // } else if (user.getRole().equals("Manager")) {
                        // Parent ManagerView = FXMLLoader
                        // .load(getClass().getResource("/com/dealership/views/ManagerView.fxml"));
                        // root = new Scene(ManagerView);
                    } else {
                        Parent inventoryView = FXMLLoader
                                .load(getClass().getResource("/com/dealership/views/InventoryView.fxml"));
                        root = new Scene(inventoryView);
                    }
                    SceneManager.switchScene(root);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Login Error");
                    alert.setContentText("Invalid ID/Email or Password.");
                    alert.showAndWait();
                    System.out.println("Invalid credentials");
                }
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
