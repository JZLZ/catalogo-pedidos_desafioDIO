package com.jzlz.order_service.model;

import java.util.List;

public class Order {
    private List<Product> products;
    private double total;

    public Order(List<Product> products) {
        this.products = products;
        this.total = products.stream().mapToDouble(Product::getPreco).sum();
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotal() {
        return total;
    }
}
