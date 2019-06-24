package com.example.pizzaapp;

public class Pizza {

    int pizzaId;
    String name;
    String details;
    Double price;
    String imageURL;

    public Pizza(int pizzaId, String name, String details, Double price, String imageURL) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.details = details;
        this.price = price;
        this.imageURL = imageURL;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
