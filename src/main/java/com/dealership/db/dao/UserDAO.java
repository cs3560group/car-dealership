package com.dealership.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dealership.db.DBConnection;
import com.dealership.models.User;

import javafx.scene.control.Alert;

@SuppressWarnings({ "CallToPrintStackTrace", "UseSpecificCatch", "unused" })
public class UserDAO {
    /**
     * Adds a new user to the database.
     *
     * @param user the User object to be added
     * @return true if the user was added successfully, false otherwise
     * @throws SQLException if a database access error occurs
     */
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

    /**
     * Validates a user by checking their email or userID and password.
     *
     * @param idOrEmail the email or userID of the user
     * @param password  the password of the user
     * @return a User object if the credentials are valid, null otherwise
     * @throws SQLException if a database access error occurs
     */
    public static User validateUser(String idOrEmail, String password) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ? OR userID = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idOrEmail);
            stmt.setString(2, String.valueOf(idOrEmail));
            stmt.setString(3, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("userID"), rs.getString("name"), rs.getString("password"),
                        rs.getString("role"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Registers a new user in the system.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void registerUser(User user) throws SQLException {
        String name = user.getName();
        String emailText = user.getEmail();
        String passwordText = user.getPassword();
        String role = user.getRole();

        // Validate input fields
        if (name.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || role.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        User newUser = new User(name, passwordText, role, emailText);
        boolean isRegistered = UserDAO.addUser(newUser); // Call UserDAO to register the user
        if (isRegistered) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"));
                users.add(user);
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void updateUser(User editingUser) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, role = ? WHERE userID = ?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, editingUser.getName());
            stmt.setString(2, editingUser.getEmail());
            stmt.setString(3, editingUser.getPassword());
            stmt.setString(4, editingUser.getRole());
            stmt.setInt(5, editingUser.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE userID = ?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}