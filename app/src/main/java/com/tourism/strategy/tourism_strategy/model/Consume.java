package com.tourism.strategy.tourism_strategy.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Consume {

    @Id
    private Long id;
    private String time;
    private double money;
    private String summary;
    private String unit;
    private int type;//消费类型

    public Consume(String time, double money, String summary,String unit,int type) {
        this.time = time;
        this.money = money;
        this.summary = summary;
        this.unit=unit;
        this.type=type;
    }

    @Generated(hash = 1989501651)
    public Consume(Long id, String time, double money, String summary, String unit,
            int type) {
        this.id = id;
        this.time = time;
        this.money = money;
        this.summary = summary;
        this.unit = unit;
        this.type = type;
    }

    @Generated(hash = 836215753)
    public Consume() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
