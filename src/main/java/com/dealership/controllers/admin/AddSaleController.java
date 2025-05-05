package com.dealership.controllers.admin;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.dealership.db.dao.CustomerDAO;
import com.dealership.db.dao.SaleDAO;
import com.dealership.models.Sale;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSaleController {

    @FXML
    private TextField priceField;
    @FXML
    private TextField paymentField;
    @FXML
    private TextField employeeIdField;
    @FXML
    private TextField vinField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerEmailField;
    @FXML
    private TextField customerPhoneField;

    @FXML
    private void handleSubmit() {
        try {
            // Get customer data
            String name = customerNameField.getText();
            String email = customerEmailField.getText();
            String phone = customerPhoneField.getText();
            int customerId = CustomerDAO.insertCustomerAndGetId(name, email, phone);

            // Get sale data
            double price = Double.parseDouble(priceField.getText());
            String payment = paymentField.getText();
            int employeeId = Integer.parseInt(employeeIdField.getText());
            String vin = vinField.getText();

            Sale sale = new Sale(0, Timestamp.valueOf(LocalDateTime.now()), price, payment, customerId, employeeId,
                    vin);
            SaleDAO.insertSale(sale);

            new Alert(Alert.AlertType.INFORMATION, "Sale and customer added successfully!").showAndWait();
            ((Stage) priceField.getScene().getWindow()).close();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }
    }
}
