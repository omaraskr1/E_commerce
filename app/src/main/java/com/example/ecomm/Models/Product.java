package com.example.ecomm.Models;

public class Product {
    private int id;
    private String name;
    private String price;
    private int quantity;
    private String description;
    private boolean addedtocart;
    private int cateid;

    public Product(int id, String name, String price, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
    public Product( String name, String price,  int quantity,String description,int cateid) {

        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.cateid=cateid;
    }


    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAddedtocart() {
        return addedtocart;
    }

    public void setAddedtocart(boolean addedtocart) {
        this.addedtocart = addedtocart;
    }

}
