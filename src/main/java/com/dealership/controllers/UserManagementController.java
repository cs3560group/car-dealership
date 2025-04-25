package com.dealership.controllers;

import com.dealership.db.dao.UserDAO;
import com.dealership.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserManagementController {
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> idColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    private final ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize the user table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        try {
            users.setAll(UserDAO.getAllUsers());
        } catch (Exception e) {
        }
        // Load users from the database and populate the table

        userTable.setItems(users);
    }

    public void handleAddUser() {
        // Logic to add a new user
    }

    public void handleEditUser() {
        // Logic to edit the selected user
    }

    public void handleDeleteUser() {
        // Logic to delete the selected user
    }
}
