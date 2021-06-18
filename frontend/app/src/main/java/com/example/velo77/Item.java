package com.example.velo77;

public class Item {

    private String id;
    private String name;
    private double price;
    private String size;
    private String collection;
    private String series;
    private String brand;
    private String type;



    public Item(String id, String name/*, double price, String size, String collection, String series, String brand, String type*/) {
        this.id = id;
        this.name = name;
        /*this.price = price;
        this.size = size;
        this.collection = collection;
        this.series = series;
        this.brand = brand;
        this.type = type;*/
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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
