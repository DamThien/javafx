package com.example.ql_skateboarding;

public class Skateboarding {
    public int id;
    public String name;
    public String brand;
    public int quantity;
    public String image;

    public Float price;
    public String quanlity;


    public Skateboarding(int id, String name,String brand,int quantity,  String image,  float price) {
        this.id = id;
        this.name = name;
        this.brand=brand;
        this.quantity=quantity;
        this.image = image;
        this.price = price;
    }
    public Skateboarding( String name,String brand,int quantity,  String image,  float price) {
        this.name = name;
        this.brand=brand;
        this.quantity=quantity;
        this.image = image;
        this.price = price;
    }

    public Skateboarding(String name,String brand,int quantity,  String image,  float price, int id) {
        this.name = name;
        this.brand=brand;
        this.quantity=quantity;
        this.image = image;
        this.price = price;
        this.id=id;
    }
}

