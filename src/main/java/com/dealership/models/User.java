package com.dealership.models;

public class User {
    private int userID;
    private String name;
    private String password;
    private String role; // "admin", "manager", "employee", "customer"
    private String email;

    public User(int userID, String name, String password, String role, String email) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User(String name, String password, String role, String email) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return userID;
    }

    public void setId(int userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
