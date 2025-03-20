package com.dealership.services;

public class Notification {
    private String message;
    private String date;
    private String NotificationID;

    public Notification(String message, String date, String NotificationID) {
        this.message = message;
        this.date = date;
        this.NotificationID = NotificationID;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getNotificationID() {
        return NotificationID;
    }
}
