package com.example.velo77.obj;


import java.io.Serializable;

public class Item implements Serializable {

    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final String size;
    private final String collection;
    private final String series;
    private final String brand;
    private final String type;



    public Item(String id, String name, String desciption, double price, String size, String collection, String series, String brand, String type) {
        this.id = id;
        this.description = desciption;
        this.name = name;
        this.price = price;
        this.size = size;
        this.collection = collection;
        this.series = series;
        this.brand = brand;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getCollection() {
        return collection;
    }

    public String getSeries() {
        return series;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }




}
