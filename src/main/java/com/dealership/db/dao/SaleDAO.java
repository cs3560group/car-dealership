package com.dealership.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dealership.db.DBConnection;
import com.dealership.models.Sale;

public class SaleDAO {

    public static void addSale(Sale sale) {
        String sql = "INSERT INTO sales (salePrice, paymentMethod, customers_customerID, employees_employeeID, VIN) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, sale.getSalePrice());
            stmt.setString(2, sale.getPaymentMethod());
            stmt.setInt(3, sale.getCustomerID());
            stmt.setInt(4, sale.getEmployeeID());
            stmt.setString(5, sale.getVin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSale(int saleID) {
        String sql = "DELETE FROM sales WHERE saleID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, saleID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSale(Sale sale) {
        String sql = "UPDATE sales SET salePrice = ?, paymentMethod = ?, customers_customerID = ?, employees_employeeID = ?, VIN = ? WHERE saleID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, sale.getSalePrice());
            stmt.setString(2, sale.getPaymentMethod());
            stmt.setInt(3, sale.getCustomerID());
            stmt.setInt(4, sale.getEmployeeID());
            stmt.setString(5, sale.getVin());
            stmt.setInt(6, sale.getSaleID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales";

        try (Connection conn = DBConnection.getConnection();
                java.sql.Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Sale sale = new Sale(
                        rs.getInt("saleID"),
                        rs.getTimestamp("saleDate"),
                        rs.getDouble("salePrice"),
                        rs.getString("paymentMethod"),
                        rs.getInt("customers_customerID"),
                        rs.getInt("employees_employeeID"),
                        rs.getString("vin"));
                sales.add(sale);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sales;
    }

    public static void insertSale(Sale sale) {
        String sql = "INSERT INTO sales (saleDate, salePrice, paymentMethod, customers_customerID, employees_employeeID, vin) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, sale.getSaleDate());
            stmt.setDouble(2, sale.getSalePrice());
            stmt.setString(3, sale.getPaymentMethod());
            stmt.setInt(4, sale.getCustomerID());
            stmt.setInt(5, sale.getEmployeeID());
            stmt.setString(6, sale.getVin());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
