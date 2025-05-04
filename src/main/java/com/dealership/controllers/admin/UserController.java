package com.dealership.controllers.admin;

import java.io.IOException;
import java.util.List;

import com.dealership.db.dao.UserDAO;
import com.dealership.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UserController {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> idCol;
    @FXML
    private TableColumn<User, String> nameCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, String> roleCol;
    @FXML
    private TableColumn<User, Void> actionCol;

    private final ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        try {
            users.setAll(UserDAO.getAllUsers());
            List<User> userList = UserDAO.getAllUsers();
            users.setAll(userList);
            userTable.setItems(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addEditButtonToTable();

        userTable.setItems(users);

    }

    private void addEditButtonToTable() {
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(e -> {
                    User selectedUser = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/com/dealership/views/admin_view/edit_user_view.fxml"));
                        Parent root = loader.load();

                        EditUserController controller = loader.getController();
                        controller.setUserData(selectedUser);
                        controller.setUserController(UserController.this);

                        Stage stage = new Stage();
                        stage.setTitle("Edit User");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                editButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });
    }

    @FXML
    private void handleAddUser() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/dealership/views/admin_view/add_user_view.fxml"));
            Parent root = loader.load();

            AddUserController addUserController = loader.getController();
            addUserController.setUserController(this);
            Stage stage = new Stage();
            stage.setTitle("Add New User");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void refreshUserTable() throws Exception {
        try {
            List<User> updatedUsers = UserDAO.getAllUsers();
            users.setAll(updatedUsers);
            userTable.setItems(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}