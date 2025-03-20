package com.dealership.services;

public class Inventory {
    private int totalStock;
    private int thresholdLimit;

    public Inventory(int totalStock, int thresholdLimit) {
        this.totalStock = totalStock;
        this.thresholdLimit = thresholdLimit;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public int getThresholdLimit() {
        return thresholdLimit;
    }
}
