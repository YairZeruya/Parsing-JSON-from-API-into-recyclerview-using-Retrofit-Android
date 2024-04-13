package com.example.parsing_json_from_nested_object_into_recyclerview_using_retrofit_android.Model;

public class Product {
    private String title;
    private double price;
    private Rating rating;
    private String image;
    private String description;

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public Rating getRating() {
        return rating;
    }

    public String getImage() {
        return image;
    }
}
