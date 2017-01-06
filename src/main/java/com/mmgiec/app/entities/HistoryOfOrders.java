package com.mmgiec.app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class HistoryOfOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "history_of_order_id")
    private List<PhoneAndQuantityInOrder> phoneAndQuantityInOrderList;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate date;

    private double totalCost;

    public HistoryOfOrders() {
    }

    public HistoryOfOrders(User user, List<PhoneAndQuantityInOrder> phoneAndQuantityInOrderList, LocalDate date) {
        this.user = user;
        this.phoneAndQuantityInOrderList = phoneAndQuantityInOrderList;
        this.date = date;
        this.totalCost = calculateCost();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PhoneAndQuantityInOrder> getPhoneAndQuantityInOrderList() {
        return phoneAndQuantityInOrderList;
    }

    public void setPhoneAndQuantityInOrderList(List<PhoneAndQuantityInOrder> phoneAndQuantityInOrderList) {
        this.phoneAndQuantityInOrderList = phoneAndQuantityInOrderList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double calculateCost() {
        double result = 0;
        for (PhoneAndQuantityInOrder item : phoneAndQuantityInOrderList) {
            result += item.getPhone().getPrice() * item.getQuantity();
        }
        return result;
    }
}
