package com.example.pizzaapp.CartItems;

public class Total {

    private static Double totalPrice;

    private static  String OrderId;

    public static String getOrderId() {
        return OrderId;
    }

    public static void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public static Double getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(Double totalPrice) {
        Total.totalPrice = totalPrice;
    }
}
