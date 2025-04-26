package com.dealership;

import com.dealership.controllers.SceneManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
        public static StackPane rootPane = new StackPane();

        @Override
        public void start(Stage stage) throws Exception {
                SceneManager.setStage(stage);
                Parent root = FXMLLoader.load(getClass().getResource("/com/dealership/views/landingView.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }

        public static void main(String[] args) {
                launch(args);
        }
}