package com.dealership.controllers.admin;

import java.io.IOException;

import com.dealership.auth.Session;
import com.dealership.controllers.SceneManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SettingController {
    
    @FXML
    private Button signOutButton;
    
    /**
     * Handles the sign-out button click event.
     * Clears the current user session and returns to the login page.
     * 
     * @throws IOException if the login view FXML cannot be loaded
     */
    @FXML
    private void handleSignOut() throws IOException {
        // Clear the current user session
        Session.setCurrentUser(null);
        
        // Load the login view
        // We need to use the correct resource path
        String loginViewPath = "/com/dealership/views/LoginView.fxml";
        Parent loginRoot = FXMLLoader.load(getClass().getResource(loginViewPath));
        
        // Create the scene without casting the root element
        Scene loginScene = new Scene(loginRoot);
        
        // Use SceneManager to switch scenes with animation
        SceneManager.switchScene(loginScene);
    }
}