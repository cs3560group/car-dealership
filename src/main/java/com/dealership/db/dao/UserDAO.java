package com.dealership.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dealership.db.DBConnection;
import com.dealership.models.User;

public class UserDAO {
    public static boolean addUser(User user) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
