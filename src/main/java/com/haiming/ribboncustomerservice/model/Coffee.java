package com.haiming.ribboncustomerservice.model;

import com.google.common.base.MoreObjects;
import org.joda.money.Money;

import java.util.Date;


public class Coffee extends BaseEntity {

    private String name;
    private Money price;

    public Coffee(){}
    public Coffee(Long id, String name, Money price, Date createTime, Date updateTime){
        this.id= id;
        this.price = price;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("price", price)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}
