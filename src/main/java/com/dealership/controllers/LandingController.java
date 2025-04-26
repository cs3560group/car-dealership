package com.dealership.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LandingController {

    /**
     * Handles the "Browse as Guest" button click event.
     * Switches to the inventory view.
     * 
     * @param event The action event triggered by the button click.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public void handleBrowseAsGuest(ActionEvent event) throws IOException {
        Parent inventoryView = FXMLLoader.load(getClass().getResource("/com/dealership/views/inventoryView.fxml"));
        Scene root = new Scene(inventoryView);
        SceneManager.switchScene(root);
    }

    /**
     * Handles the "Login" button click event.
     * Switches to the login view.
     * 
     * @param event The action event triggered by the button click.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public void handleGoToLogin(ActionEvent event) throws IOException {
        Parent loginView = FXMLLoader.load(getClass().getResource("/com/dealership/views/loginView.fxml"));
        Scene root = new Scene(loginView);
        SceneManager.switchScene(root);
    }

    /**
     * Handles the "Register" button click event.
     * Switches to the register view.
     * 
     * @param event The action event triggered by the button click.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public void handleGoToRegister(ActionEvent event) throws IOException {
        Parent registerView = FXMLLoader.load(getClass().getResource("/com/dealership/views/registerView.fxml"));
        Scene root = new Scene(registerView);
        SceneManager.switchScene(root);
    }
}
