package com.tourism.strategy.tourism_strategy.model;

import java.util.List;

public class AttractionDetail extends MultipleItem{
    private int id;
    private String name_zh_cn;
    private String name_en;
    private String description;
    private String tips_html;
    private int photos_count;
    private int attraction_trips_count;
    private List<AttractionTags> attraction_trip_tags;

    public List<AttractionTags> getAttraction_trip_tags() {
        return attraction_trip_tags;
    }

    public void setAttraction_trip_tags(List<AttractionTags> attraction_trip_tags) {
        this.attraction_trip_tags = attraction_trip_tags;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public int getAttraction_trips_count() {
        return attraction_trips_count;
    }

    public void setAttraction_trips_count(int attraction_trips_count) {
        this.attraction_trips_count = attraction_trips_count;
    }

    private String image_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTips_html() {
        return tips_html;
    }

    public void setTips_html(String tips_html) {
        this.tips_html = tips_html;
    }
}
