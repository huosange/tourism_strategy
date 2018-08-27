package com.tourism.strategy.tourism_strategy.model;

import java.util.List;

public class Category {
    private String category;
    private List<Country> destinations;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Country> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Country> destinations) {
        this.destinations = destinations;
    }
}
