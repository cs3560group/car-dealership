package com.dealership.auth;

import com.dealership.models.User;

public class Session {
    private static Boolean loggedIn = false;
    private static User currentUser = null;
    
    public static Boolean isLoggedIn() {
        return loggedIn;
    }
    public static void login() {
        loggedIn = true;
    }
    public static void logout() {
        loggedIn = false;
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static boolean hasRole(String role) {
        return currentUser != null && role != null && role.equalsIgnoreCase(currentUser.getRole());
    }
}
