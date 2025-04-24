package com.dealership.controllers;

import java.io.IOException;

import com.dealership.auth.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    @SuppressWarnings("unused")
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password is empty.");
        } else if (username.equals("admin") && password.equals("admin")) {
            Session.login();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dealership/views/InventoryView.fxml"));
            Parent inventoryView = loader.load();
            Scene root = new Scene(inventoryView);
            SceneManager.switchScene(root);
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    @FXML
    @SuppressWarnings("unused")
    private void goBack() {
        SceneManager.goBack();
    }
}
