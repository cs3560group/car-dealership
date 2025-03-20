package com.dealership;

import com.dealership.models.Customer;

public class Main {
    public static void main(String[] args) throws Exception {
        Customer customer = new Customer("John", "1234567890", "JohnSDas@sdjiasj.com", "1234");
        customer.printCustomerInfo();
    }
}
