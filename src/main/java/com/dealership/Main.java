package com.dealership;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
        public static StackPane rootPane = new StackPane();

        @Override
        public void start(Stage stage) throws Exception {
                Parent loginView = FXMLLoader.load(getClass().getResource("/com/dealership/ui/LandingPage.fxml"));
                rootPane.getChildren().add(loginView);
                
                Scene scene = new Scene(rootPane, 1000, 600);
                stage.setTitle("Car Dealership");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
        }

        public static void switchView(Parent newView) {
        FadeTransition ft = new FadeTransition(Duration.millis(300), rootPane);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> {
            rootPane.getChildren().setAll(newView);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), rootPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        ft.play();
    }
        public static void main(String[] args) {
                launch(args);
        }
}