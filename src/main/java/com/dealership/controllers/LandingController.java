package com.dealership.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LandingController {
    public void handleBrowseAsGuest(ActionEvent event) throws IOException {
        Parent inventoryView = FXMLLoader.load(getClass().getResource("/com/dealership/views/InventoryView.fxml"));
        Scene root = new Scene(inventoryView);
        SceneManager.switchScene(root);
    }

    public void handleGoToLogin(ActionEvent event) throws IOException {
        Parent loginView = FXMLLoader.load(getClass().getResource("/com/dealership/views/LoginView.fxml"));
        Scene root = new Scene(loginView);
        SceneManager.switchScene(root);
    }

    public void handleGoToRegister(ActionEvent event) throws IOException {
        Parent registerView = FXMLLoader.load(getClass().getResource("/com/dealership/views/RegisterView.fxml"));
        Scene root = new Scene(registerView);
        SceneManager.switchScene(root);
    }
}
