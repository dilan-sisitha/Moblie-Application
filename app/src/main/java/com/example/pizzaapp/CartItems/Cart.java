package com.example.pizzaapp.CartItems;

public class Cart {
    int order_Id;
    String pizzpizza_type;
    int quantity;
    Double price;

    public Cart(int order_Id, String pizzpizza_type, int quantity, Double price) {
        this.order_Id = order_Id;
        this.pizzpizza_type = pizzpizza_type;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(int order_Id) {
        this.order_Id = order_Id;
    }

    public String getPizzpizza_type() {
        return pizzpizza_type;
    }

    public void setPizzpizza_type(String pizzpizza_type) {
        this.pizzpizza_type = pizzpizza_type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
