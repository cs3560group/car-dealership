package com.dealership;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
        @Override
        public void start(Stage stage) throws Exception {
                Parent root = FXMLLoader.load(getClass().getResource("/com/dealership/ui/InventoryView.fxml"));
                Scene scene = new Scene(root, 1000, 600);
                stage.setTitle("Car Dealership Inventory");
                stage.setScene(scene);
                stage.show();
        }

        public static void main(String[] args) {
                launch(args);
        }
}