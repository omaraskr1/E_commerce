package com.example.ecomm.Models;

public class ChartModel {
    private String proName;
    private int proQuantity;
    public ChartModel(String pn, int pq){
        this.proName=pn;
        this.proQuantity=pq;
    }

    public String getProName() {
        return proName;
    }

    public int getProQuantity() {
        return proQuantity;
    }
}
