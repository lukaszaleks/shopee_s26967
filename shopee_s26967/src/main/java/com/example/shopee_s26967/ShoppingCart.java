package com.example.shopee_s26967;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private Customer customer;
    private List<String> itemName;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.itemName = new ArrayList<>();
    }

    public List<String> getItemNames() {
        return itemName;
    }

    public void addItem(String item) {
        this.itemName.add(item);
    }

    public Customer getCustomer() {
        return customer;
    }
}
