package com.dealership.ui.controllers;

import java.io.IOException;

import com.dealership.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LandingController {
    public void browseAsGuest(ActionEvent event) throws IOException {
        Parent inventoryView = FXMLLoader.load(getClass().getResource("/com/dealership/ui/InventoryView.fxml"));
        Main.switchView(inventoryView);
    }
}
