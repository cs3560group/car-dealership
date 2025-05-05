package com.dealership.controllers.admin;

import java.io.IOException;
import java.sql.Timestamp;

import com.dealership.db.dao.SaleDAO;
import com.dealership.models.Sale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SalesController {

    @FXML
    private TableView<Sale> salesTable;
    @FXML
    private TableColumn<Sale, Integer> saleIdCol;
    @FXML
    private TableColumn<Sale, Timestamp> saleDateCol;
    @FXML
    private TableColumn<Sale, Double> priceCol;
    @FXML
    private TableColumn<Sale, String> paymentCol;
    @FXML
    private TableColumn<Sale, Integer> customerIdCol;
    @FXML
    private TableColumn<Sale, Integer> employeeIdCol;
    @FXML
    private TableColumn<Sale, String> vinCol;

    @FXML
    private TextField priceField;
    @FXML
    private TextField paymentField;
    @FXML
    private TextField employeeIdField;
    @FXML
    private TextField vinField;

    private final ObservableList<Sale> saleList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        saleIdCol.setCellValueFactory(cellData -> cellData.getValue().saleIDProperty().asObject());
        saleDateCol.setCellValueFactory(cellData -> cellData.getValue().saleDateProperty());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().salePriceProperty().asObject());
        paymentCol.setCellValueFactory(cellData -> cellData.getValue().paymentMethodProperty());
        customerIdCol.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        employeeIdCol.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
        vinCol.setCellValueFactory(cellData -> cellData.getValue().vinProperty());

        saleList.setAll(SaleDAO.getAllSales());
        salesTable.setItems(saleList);

    }

    @FXML
    private void handleAddSale() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/dealership/views/admin_view/add_sale_view.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root, 400, 500));
            popupStage.setTitle("Add Sale");
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.showAndWait();

            // Refresh table after closing popup
            saleList.setAll(SaleDAO.getAllSales());

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to open popup: " + e.getMessage()).showAndWait();
        }
    }

}
