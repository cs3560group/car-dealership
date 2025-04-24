package com.dealership.auth;

public class Session {
    private static Boolean loggedIn = false;

    public static Boolean isLoggedIn() {
        return loggedIn;
    }
    public static void login() {
        loggedIn = true;
    }
    public static void logout() {
        loggedIn = false;
    }
}
