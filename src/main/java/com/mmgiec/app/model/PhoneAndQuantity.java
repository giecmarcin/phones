package com.mmgiec.app.model;

import com.mmgiec.app.entities.Phone;

public class PhoneAndQuantity {
    private Phone phone;
    private int quantity;

    public PhoneAndQuantity() {
    }

    public PhoneAndQuantity(Phone phone, int quantity) {
        this.phone = phone;
        this.quantity = quantity;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
