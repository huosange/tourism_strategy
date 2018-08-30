package com.tourism.strategy.tourism_strategy.model;

public class Zone {
    private int id;
    private String name_zh_cn;
    private String name_en;
    private int poi_count;
    private int plans_count;
    private String image_url;

    public int getPlans_count() {
        return plans_count;
    }

    public void setPlans_count(int plans_count) {
        this.plans_count = plans_count;
    }

    public int getPoi_count() {
        return poi_count;
    }

    public void setPoi_count(int poi_count) {
        this.poi_count = poi_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_zh_cn() {
        return name_zh_cn;
    }

    public void setName_zh_cn(String name_zh_cn) {
        this.name_zh_cn = name_zh_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
