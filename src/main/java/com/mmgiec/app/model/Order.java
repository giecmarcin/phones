package com.mmgiec.app.model;


import java.util.List;

public class Order {
    private String customerEmail;
    private List<PhoneAndQuantity> products;

    public Order() {
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<PhoneAndQuantity> getProducts() {
        return products;
    }

    public void setProducts(List<PhoneAndQuantity> products) {
        this.products = products;
    }
}
