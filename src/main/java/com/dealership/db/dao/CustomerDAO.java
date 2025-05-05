package com.dealership.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dealership.db.DBConnection;

public class CustomerDAO {
    public static int insertCustomerAndGetId(String name, String email, String phone) throws Exception {
        String sql = "INSERT INTO customers (name, email, phone_number) VALUES (?, ?, ?)";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Inserting customer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new Exception("Inserting customer failed, no ID obtained.");
                }
            }
        }
    }
}
