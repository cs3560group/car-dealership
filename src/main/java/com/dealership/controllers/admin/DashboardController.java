package com.dealership.controllers.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dealership.db.DBConnection;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {
    @FXML
    private Label employeeValue;

    @FXML
    private Label totalSaleValue;

    @FXML
    private Label totalInventoryValue;

    @FXML
    public void initialize() {
        fetchDashboardData();

    }

    private void fetchDashboardData() {
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement()) {

            // Count employees
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM users");
            int employeeCount = 0;
            if (rs1.next())
                employeeCount = rs1.getInt(1);
            employeeValue.setText(String.valueOf(employeeCount));

            // Total sales
            totalSaleValue.setText("$ 0.00"); // Initialize to 0.00

            // Count inventory
            ResultSet rs3 = stmt.executeQuery("SELECT COUNT(*) FROM vehicles");
            int inventoryCount = 0;
            if (rs3.next())
                inventoryCount = rs3.getInt(1);
            totalInventoryValue.setText(String.valueOf(inventoryCount));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
