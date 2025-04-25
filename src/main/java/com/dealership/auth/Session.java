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
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
