package com.haiming.ribboncustomerservice.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class CoffeeOrder extends BaseEntity implements Serializable {
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Coffee> getItems() {
        return items;
    }

    public void setItems(List<Coffee> items) {
        this.items = items;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    private String customer;
    private List<Coffee> items;
    private OrderState state;

    public CoffeeOrder() {
    }
    public CoffeeOrder(Long id, String customer, List<Coffee> items, OrderState state, Date createTime, Date updatetime){
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.createTime = createTime;
        this.updateTime = updatetime;
        this.state = state;
    }
}
