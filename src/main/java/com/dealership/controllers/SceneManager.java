package com.dealership.controllers;

import java.util.Stack;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneManager {
    private static Stage stage;
    private static final Stack<Scene> sceneStack = new Stack<>();

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void switchScene(Scene newScene) {
        if (stage.getScene() != null) {
            sceneStack.push(stage.getScene());

            // Fade out the current scene
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), stage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                stage.setScene(newScene);

                // Fade in the new scene
                FadeTransition fadeIn = new FadeTransition(Duration.millis(300), newScene.getRoot());
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();
        } else {
            // No previous scene (first load), no animation
            stage.setScene(newScene);
        }
    }

    public static void goBack() {
        if (!sceneStack.isEmpty()) {
            Scene previousScene = sceneStack.pop();

            // Fade out current
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), stage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                stage.setScene(previousScene);

                // Fade in previous
                FadeTransition fadeIn = new FadeTransition(Duration.millis(300), previousScene.getRoot());
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();
        } else {
            System.out.println("No previous scene to return to.");
        }
    }
}
