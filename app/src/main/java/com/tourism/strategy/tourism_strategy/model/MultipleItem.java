package com.tourism.strategy.tourism_strategy.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultipleItem implements MultiItemEntity {

    public static final int TEXT_AND_IMAGES = 1;
    public static final int TEXT = 2;
    public static final int HEADER=3;
    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
