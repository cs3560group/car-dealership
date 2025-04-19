package com.dealership.services;
 
 public class Sales {
     private String saleID;
     private String vehicleID;
     private String customerID;
     private String employeeID;
     private double salePrice;
     private String date;
     private String paymentMethod;
 
     public Sales(String saleID, String vehicleID, String customerID, String employeeID, double salePrice, String date,
             String paymentMethod) {
         this.saleID = saleID;
         this.vehicleID = vehicleID;
         this.customerID = customerID;
         this.employeeID = employeeID;
         this.salePrice = salePrice;
         this.date = date;
         this.paymentMethod = paymentMethod;
     }
 
     public String getSaleID() {
         return saleID;
     }
 
     public String getVehicleID() {
         return vehicleID;
     }
 
     public String getCustomerID() {
         return customerID;
     }
 
     public String getEmployeeID() {
         return employeeID;
     }
 
     public double getSalePrice() {
         return salePrice;
     }
 
     public String getDate() {
         return date;
     }
 
     public String getPaymentMethod() {
         return paymentMethod;
     }
 }