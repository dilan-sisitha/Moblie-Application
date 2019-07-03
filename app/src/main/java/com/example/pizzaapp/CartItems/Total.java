package com.example.pizzaapp.CartItems;

public class Total {

    private static Double totalPrice;

    public static Double getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(Double totalPrice) {
        Total.totalPrice = totalPrice;
    }
}
