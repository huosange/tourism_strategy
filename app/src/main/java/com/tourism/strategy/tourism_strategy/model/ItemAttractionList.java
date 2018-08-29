package com.tourism.strategy.tourism_strategy.model;

import java.util.List;

public class ItemAttractionList extends MultipleItem{

    private List<ItemAttraction> list;
    private String title;

    public ItemAttractionList(String title){
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemAttraction> getList() {
        return list;
    }

    public void setList(List<ItemAttraction> list) {
        this.list = list;
    }
}
