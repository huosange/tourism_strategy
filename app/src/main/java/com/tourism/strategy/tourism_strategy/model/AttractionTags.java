package com.tourism.strategy.tourism_strategy.model;

import java.util.List;

public class AttractionTags extends MultipleItem{
    private int id;
    private String name;
    private int display_count;
    private List<AttractionContent> attraction_contents;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplay_count() {
        return display_count;
    }

    public void setDisplay_count(int display_count) {
        this.display_count = display_count;
    }

    public List<AttractionContent> getAttraction_contents() {
        return attraction_contents;
    }

    public void setAttraction_contents(List<AttractionContent> attraction_contents) {
        this.attraction_contents = attraction_contents;
    }
}
