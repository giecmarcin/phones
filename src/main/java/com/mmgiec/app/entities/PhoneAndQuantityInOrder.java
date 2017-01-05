package com.mmgiec.app.entities;

import javax.persistence.*;

@Entity
public class PhoneAndQuantityInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Phone phone;
    private int quantity;

    public PhoneAndQuantityInOrder() {
    }

    public PhoneAndQuantityInOrder(Phone phone, int quantity) {
        this.phone = phone;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
