package com.dealership.controllers.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch", "unused"})
public class SlideController implements Initializable {
        @FXML
        private BorderPane bp;
        @FXML
        private AnchorPane ap;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
                loadPage("dashboard_view");
        }

        @FXML
        private void homeHandler(MouseEvent event) {
                loadPage("dashboard_view");
        }

        @FXML
        private void userHandler(MouseEvent event) {
                loadPage("user_view");
        }

        @FXML
        private void inventoryHandler(MouseEvent event) {
                loadPage("inventory_view");
        }

        @FXML
        private void saleHandler(MouseEvent event) {
                loadPage("sales_view");
        }

        @FXML
        private void settingHandler(MouseEvent event) {
                loadPage("setting_view");
        }

        private void loadPage(String page) {
                try {
                        // Load the view as a Parent instead of AnchorPane
                        // This allows loading any type of layout (BorderPane, VBox, etc.)
                        Parent view = FXMLLoader.load(
                                getClass().getResource("/com/dealership/views/admin_view/" + page + ".fxml"));
                        
                        // Set anchors if the view is to be placed in an AnchorPane
                        if (view instanceof AnchorPane) {
                                AnchorPane.setTopAnchor(view, 0.0);
                                AnchorPane.setBottomAnchor(view, 0.0);
                                AnchorPane.setLeftAnchor(view, 0.0);
                                AnchorPane.setRightAnchor(view, 0.0);
                        } else {
                                // For other layout types like BorderPane, still set the anchors
                                // This ensures the view fills the available space
                                AnchorPane.setTopAnchor(view, 0.0);
                                AnchorPane.setBottomAnchor(view, 0.0);
                                AnchorPane.setLeftAnchor(view, 0.0);
                                AnchorPane.setRightAnchor(view, 0.0);
                        }
                        
                        // Set the loaded view to the center of the BorderPane
                        bp.setCenter(view);
                } catch (Exception e) {
                        System.out.println(e.toString());
                        e.printStackTrace(); // Add stack trace for better debugging
                }
        }
}