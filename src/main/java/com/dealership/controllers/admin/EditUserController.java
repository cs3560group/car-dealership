package com.dealership.controllers.admin;

import com.dealership.db.dao.UserDAO;
import com.dealership.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch", "unused"})
public class EditUserController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> roleComboBox;

    private User editingUser;
    private UserController userController;

    @FXML
    public void initialize() {
        roleComboBox.getItems().addAll("Admin", "Employee", "Manager");
    }

    
    public void setUserData(User user) {
        this.editingUser = user;
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        passwordField.setText(user.getPassword());
        roleComboBox.setValue(user.getRole().substring(0, 1).toUpperCase() + user.getRole().substring(1));
    }

    public void setUserController(UserController controller) {
        this.userController = controller;
    }

    @FXML
    private void handleSave() {
        editingUser.setName(nameField.getText());
        editingUser.setEmail(emailField.getText());
        editingUser.setPassword(passwordField.getText());
        editingUser.setRole(roleComboBox.getValue().toLowerCase());

        try {
            UserDAO.updateUser(editingUser);
            showAlert(Alert.AlertType.INFORMATION, "User added successfully.");
            if (userController != null) {
                userController.refreshUserTable();

            }
            ((Stage) nameField.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this user?",
                ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    UserDAO.deleteUser(editingUser.getId());
                    if (userController != null) {
                        userController.refreshUserTable();
                    }
                    ((Stage) nameField.getScene().getWindow()).close();
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Failed to delete user.");
                }
            }
        });
    }

    @FXML
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }
}
