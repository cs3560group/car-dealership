package com.dealership.models;

public class User {
    private String name;
    private String password;
    private String role; // "admin", "manager", "employee", "customer"
    private String email;

    public User(String username, String password, String role, String email) {
        this.name = username;
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
}
